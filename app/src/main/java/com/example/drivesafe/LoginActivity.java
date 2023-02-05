package com.example.drivesafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


import com.example.drivesafe.Signup.SignupActivity;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText login_EDT_email, login_EDT_password;
    private MaterialButton login_BTN_login;
    private TextView login_BTN_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        findViews();
        initViews();

    }

    private void findViews() {
        login_EDT_email = findViewById(R.id.login_EDT_email);
        login_EDT_password = findViewById(R.id.login_EDT_password);
        login_BTN_login = findViewById(R.id.login_BTN_login);
        login_BTN_signup = findViewById(R.id.login_BTN_signup);
    }

    private void initViews() {
        login_BTN_login.setOnClickListener(view -> {
            if (validateEmailAndPassword() && checkUser())
                performLogin();
        });
        login_BTN_signup.setOnClickListener(view -> {
            if (checkUser())
                gotoSignup();
        });
    }



    private boolean checkUser() {
        // TODO check if user exist in DB
        return true;
    }

    private boolean validateEmailAndPassword() {
        // TODO check email and password
        String email = "" + login_EDT_email.getText();
        String password = "" + login_EDT_password.getText();
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!password.isEmpty()) {
                return true;
            }else{
                Toast.makeText(this, "password is not Valid!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(this, "email is not Valid!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void performLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void gotoSignup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}