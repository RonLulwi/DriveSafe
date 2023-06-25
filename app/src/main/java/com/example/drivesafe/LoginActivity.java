package com.example.drivesafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.drivesafe.Entities.UserEntity;
import com.example.drivesafe.Main.MainActivity;
import com.example.drivesafe.Signup.SignupActivity;
import com.example.drivesafe.Utils.MSP;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;


public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText login_EDT_id, login_EDT_password;
    String id, password;
    private MaterialButton login_BTN_login;
    private TextView login_BTN_signup;
    private ImageView login_IMG_fingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        findViews();
        initViews();

    }

    private void findViews() {
        login_EDT_id = findViewById(R.id.login_EDT_id);
        login_EDT_password = findViewById(R.id.login_EDT_password);
        login_BTN_login = findViewById(R.id.login_BTN_login);
        login_BTN_signup = findViewById(R.id.login_BTN_signup);
        login_IMG_fingerprint = findViewById(R.id.login_IMG_fingerprint);
    }

    private void initViews() {
        login_BTN_login.setOnClickListener(view -> {
            if(validateIdAndPassword())
                signIn(this.id, this.password);
        });
        login_BTN_signup.setOnClickListener(view -> goToAnotherActivity(SignupActivity.class));
        login_IMG_fingerprint.setOnClickListener(v -> Toast.makeText(LoginActivity.this, "Fingerprint Authentication will be available at the next version", Toast.LENGTH_SHORT).show());
    }

    private void signIn(String id, String password){
        new DriveSafeController().login(id, password, new DriveSafeController.CallBack_logIn() {
            @Override
            public void logIn(UserEntity user) {
                Gson gson = new Gson();
                String userJson = gson.toJson(user);
                MSP.getInstance().putString("USER", userJson);
                goToAnotherActivity(MainActivity.class);
            }
        });
    }

    private boolean validateIdAndPassword() {
        this.id = "" + login_EDT_id.getText();
        this.password = "" + login_EDT_password.getText();
        if(!id.isEmpty() && SignUpManager.validateID(id)){
            if(!password.isEmpty()) {
                return true;
            }else{
                Toast.makeText(this, "password is not Valid!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(this, "id is not Valid!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void goToAnotherActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}