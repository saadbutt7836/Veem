package com.wgorganizaton.veem.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.wgorganizaton.veem.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseLoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.user_sign_in)
    Button User_SignIn;
    @BindView(R.id.tablet_sign_in)
    Button Tablet_Sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose_login);

        ButterKnife.bind(this);


//        CLICK LISTENERS
        User_SignIn.setOnClickListener(this);
        Tablet_Sign.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_sign_in:
                Intent UserIntent = new Intent(this, MainActivity.class);
                startActivity(UserIntent);
                break;

            case R.id.tablet_sign_in:
                Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show();
//                Intent AdminIntent = new Intent(this, MainActivity.class);
//                startActivity(AdminIntent);
                break;
        }
    }
}