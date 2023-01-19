package com.example.drivesafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private MaterialButton signup_BTN_next, signup_BTN_back;
    private FirstSignupPageFragments firstSignupPageFragments;
    private SecondSignupPageFragments secondSignupPageFragments;
    private ThirdSignupPageFragments thirdSignupPageFragments;
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
        firstSignupPageFragments = new FirstSignupPageFragments();
        secondSignupPageFragments = new SecondSignupPageFragments();
        thirdSignupPageFragments = new ThirdSignupPageFragments();
    }

    private void initViews() {
        pages = new Object[]{firstSignupPageFragments, secondSignupPageFragments, thirdSignupPageFragments};
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