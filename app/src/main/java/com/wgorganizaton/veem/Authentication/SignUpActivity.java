package com.wgorganizaton.veem.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wgorganizaton.veem.R;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


    //    WIDGET CASTINGS
    @BindView(R.id.already_user)
    TextView Already_User;
    @BindView(R.id.f_name)
    EditText F_Name;
    @BindView(R.id.l_name)
    EditText L_Name;
    @BindView(R.id.sign_up_email)
    EditText Email;
    @BindView(R.id.sign_up_pass)
    EditText Password;
    @BindView(R.id.cnfm_pass)
    EditText Confirm_Password;
    @BindView(R.id.create)
    Button Create;


    //    VARIABLES
    private String fName = null,
            lName = null,
            email = null,
            profileImg = "https://firebasestorage.googleapis.com/v0/b/veem-dde89.appspot.com/o/default_img.png?alt=media&token=f7f79b10-0043-4352-bf7b-e7ebaffaf6d8",
            password = null,
            confirmPass = null,
            userId = null;


    //    FIREBASE
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, UserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        FirebaseCasting();


        Already_User.setOnClickListener(this);
        Create.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.already_user:
                startActivity(new Intent(this, SignInActivity.class));
                break;
            case R.id.create:
                GetInputsFromFields();
        }
    }

    //    =============================================================== METHODS CALLING ====================================================================

    private void FirebaseCasting() {
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        UserRef = mDatabase.child("Users");
    }

    private void GetInputsFromFields() {
        fName = F_Name.getText().toString().trim();
        lName = L_Name.getText().toString().trim();
        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();
        confirmPass = Confirm_Password.getText().toString().trim();


        if (!fName.isEmpty() && !lName.isEmpty() && !email.isEmpty()
                && !password.isEmpty() && !confirmPass.isEmpty() && password.equals(confirmPass)) {

            SignUpUser(email, password);
        } else {
            Toast.makeText(this, "Wrong Information", Toast.LENGTH_SHORT).show();
        }


    }

    private void SignUpUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification();

                            SavedInfoToDatabase();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(SignUpActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void SavedInfoToDatabase() {
        userId = mAuth.getCurrentUser().getUid();
        Map<String, Object> Map = new HashMap<>();

        Map.put("f_name", fName);
        Map.put("l_name", lName);
        Map.put("email", email);
        Map.put("profile_img", profileImg);

        UserRef.child(userId).updateChildren(Map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Verification email sent to your email id. Please verify before login", Toast.LENGTH_SHORT).show();
                    mAuth.signOut();
                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                } else {
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}