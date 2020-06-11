package com.wgorganizaton.veem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.wgorganizaton.veem.Authentication.SignInActivity;
import com.wgorganizaton.veem.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.profile_img)
    CircularImageView Profile_Img;
    @BindView(R.id.dash_f_name)
    TextView F_Name;
    @BindView(R.id.user_id)
    TextView User_Id;
    @BindView(R.id.audio_call)
    LinearLayout Audio_Call;
    @BindView(R.id.video_call)
    LinearLayout Video_Call;
    @BindView(R.id.text_sms)
    LinearLayout Text_Sms;
    @BindView(R.id.setting)
    LinearLayout Settings;

    //    VARIABLES
    private String userId;


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, UserRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (CheckUserLogin()) {

            FirebaseCasting();

            RetrieveUserInfo();
        }


//        CLICK LISTENERS
        Profile_Img.setOnClickListener(this);
        Audio_Call.setOnClickListener(this);
        Video_Call.setOnClickListener(this);
        Text_Sms.setOnClickListener(this);
        Settings.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_img:
                Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.audio_call:
                Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.video_call:
                Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_sms:
                Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);

                break;
        }
    }

    //    ==================================================== CALLING METHODS ======================================================
    private void FirebaseCasting() {
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        UserRef = mDatabase.child("Users");
    }

    private void RetrieveUserInfo() {
        UserRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    String img = dataSnapshot.child("profile_img").getValue().toString();
                    String fName = dataSnapshot.child("f_name").getValue().toString();


                    Picasso.get().load(img).into(Profile_Img);
                    F_Name.setText("Hi, " + fName);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean CheckUserLogin() {
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser == null) {
            Intent intent = new Intent(this, SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            startActivity(intent);

            return false;
        } else {
            return true;
        }
    }


}