package com.wgorganizaton.veem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wgorganizaton.veem.R;

import butterknife.BindView;

public class SubscriptionActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tablet_id)
    EditText Tablet_Id;
    @BindView(R.id.paypal_click)
    LinearLayout Paypal_Click;
    @BindView(R.id.credit_card_click)
    LinearLayout Credit_Card_Click;

    //    FIREBASE
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase, UserRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        getSupportActionBar().setTitle(getString(R.string.subscribe));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FirebaseCasting();


//        CLICK LISTENERS
        Paypal_Click.setOnClickListener(this);
        Credit_Card_Click.setOnClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paypal_click:
                Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.credit_card_click:
                Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //    ==================================================== CALLING METHODS ======================================================
    private void FirebaseCasting() {
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        UserRef = mDatabase.child("Users");
    }


}