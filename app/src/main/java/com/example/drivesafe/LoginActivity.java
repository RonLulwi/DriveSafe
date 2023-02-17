package com.example.drivesafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;


import com.example.drivesafe.Signup.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText login_EDT_email, login_EDT_password;
    String email, password;
    private MaterialButton login_BTN_login;
    private TextView login_BTN_signup;
    
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
        
        login_BTN_login.setOnClickListener(view -> {
            if(validateEmailAndPassword())
                signIn(this.email, this.password);
        });
        login_BTN_signup.setOnClickListener(view -> goToAnotherActivity(SignupActivity.class));
    }

    
    
    
    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful())
                        goToAnotherActivity(HomePageActivity.class);
                    else
                        Toast.makeText(LoginActivity.this, "Authentication failed, Please try again.", Toast.LENGTH_SHORT).show();
                });
    }

    private boolean validateEmailAndPassword() {
        this.email = "" + login_EDT_email.getText();
        this.password = "" + login_EDT_password.getText();
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

    private void goToAnotherActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}