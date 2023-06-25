package com.example.drivesafe.Signup;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import com.example.drivesafe.CallBack_SignupProtocol;
import com.example.drivesafe.DriveSafeController;
import com.example.drivesafe.Entities.Car;
import com.example.drivesafe.Entities.UserEntity;
import com.example.drivesafe.LoginActivity;
import com.example.drivesafe.R;
import com.google.android.material.button.MaterialButton;

public class SignupActivity extends AppCompatActivity {

    private MaterialButton signup_BTN_next, signup_BTN_back;
    private FragmentsSignupPage01 fragmentsSignupPage01;
    private FragmentsSignupPage02 fragmentsSignupPage02;
    private FragmentsSignupPage03 fragmentsSignupPage03;
    private int currentPage;
    private UserEntity newUser;


    CallBack_SignupProtocol callBack_signupProtocol = new CallBack_SignupProtocol() {
        @Override
        public void personalInfo(String firstName, String lastName, String address, String city) {
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setAddress(address);
            newUser.setCity(city);
        }

        @Override
        public void carInfo(Car car) {
            newUser.setCar(car);
        }

        @Override
        public void userEmailAndPassword(String email, String password, String id) {
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setId(id);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);
        findViews();
        initViews();
    }

    private void findViews() {
        signup_BTN_next = findViewById(R.id.signup_BTN_next);
        signup_BTN_back = findViewById(R.id.signup_BTN_back);
        fragmentsSignupPage01 = new FragmentsSignupPage01();
        fragmentsSignupPage01.setCallBack_SignupProtocol(this.callBack_signupProtocol);
        fragmentsSignupPage02 = new FragmentsSignupPage02();
        fragmentsSignupPage02.setCallBack_SignupProtocol(this.callBack_signupProtocol);
        fragmentsSignupPage03 = new FragmentsSignupPage03();
        fragmentsSignupPage03.setCallBack_SignupProtocol(this.callBack_signupProtocol);
    }

    private void initViews() {
        currentPage = 0;
        newUser = new UserEntity();
        getSupportFragmentManager().beginTransaction().add(R.id.signup_FLY_frame, fragmentsSignupPage01).commit();

        signup_BTN_next.setOnClickListener(view -> {
           switch (currentPage){
               case 0:
                   if(fragmentsSignupPage01.isInputValid()) {
                       getSupportFragmentManager().beginTransaction().replace(R.id.signup_FLY_frame, fragmentsSignupPage02).commit();
                       currentPage++;
                   }
                   break;
               case 1:
                   if(fragmentsSignupPage02.isInputValid()) {
                       getSupportFragmentManager().beginTransaction().replace(R.id.signup_FLY_frame, fragmentsSignupPage03).commit();
                       signup_BTN_next.setText("Finish");
                       currentPage++;
                   }
                   break;
               case 2:
                   if(fragmentsSignupPage03.isInputValid()) {
                       saveUserToDB(this.newUser);
                   }
                   break;
           }
        });

        signup_BTN_back.setOnClickListener(view -> {

            switch (currentPage){
                case 0:
                    goToAnotherActivity(LoginActivity.class);
                    break;
                case 1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.signup_FLY_frame, fragmentsSignupPage01).commit();
                    currentPage--;
                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.signup_FLY_frame, fragmentsSignupPage02).commit();
                    signup_BTN_next.setText("Next");
                    currentPage--;
                    break;
            }
        });


    }

    private void saveUserToDB(UserEntity newUser) {
        new DriveSafeController().saveUserToDB(newUser, new DriveSafeController.CallBack_createUser() {
            @Override
            public void registerUser(UserEntity user) {
                Toast.makeText(SignupActivity.this, "Successes!", Toast.LENGTH_SHORT).show();
                goToAnotherActivity(LoginActivity.class);

            }
        });
    }

    private void goToAnotherActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}