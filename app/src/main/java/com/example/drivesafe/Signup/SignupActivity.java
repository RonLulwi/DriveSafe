package com.example.drivesafe.Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Window;

import com.example.drivesafe.R;
import com.google.android.material.button.MaterialButton;

public class SignupActivity extends AppCompatActivity {

    private MaterialButton signup_BTN_next, signup_BTN_back;
    private FragmentsSignupPage01 fragmentsSignupPage01;
    private FragmentsSignupPage02 fragmentsSignupPage02;
    private FragmentsSignupPage03 fragmentsSignupPage03;
    private Object[] pages;
    private int currentPage;


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
        fragmentsSignupPage02 = new FragmentsSignupPage02();
        fragmentsSignupPage03 = new FragmentsSignupPage03();
    }

    private void initViews() {
        pages = new Object[]{fragmentsSignupPage01, fragmentsSignupPage02, fragmentsSignupPage03};
        currentPage = 0;
        getSupportFragmentManager().beginTransaction().add(R.id.signup_FLY_frame, (Fragment) pages[currentPage]).commit();

        signup_BTN_next.setOnClickListener(view -> {
           if(currentPage < 2) {
               currentPage++;
               getSupportFragmentManager().beginTransaction().replace(R.id.signup_FLY_frame, (Fragment) pages[currentPage]).commit();
           }
        });

        signup_BTN_back.setOnClickListener(view -> {
            if(currentPage > 0) {
                currentPage--;
                getSupportFragmentManager().beginTransaction().replace(R.id.signup_FLY_frame, (Fragment) pages[currentPage]).commit();
            }

        });


    }
}