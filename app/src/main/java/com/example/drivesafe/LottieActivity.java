package com.example.drivesafe;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;
import com.example.drivesafe.Signup.SignupActivity;

public class LottieActivity extends AppCompatActivity {
    private LottieAnimationView lottie_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lottie);
        lottie_view = findViewById(R.id.lottie_view);
        lottie_view.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                goToFirstPage();
            }
        });
    }

    private void goToFirstPage() {
        Intent intent;
        if(isFirstUse())
            intent = new Intent(this, SignupActivity.class);
        else
            intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    private boolean isFirstUse() {
        // TODO: check if is the first use of the client via 'FireBase DB' OR Application
        return false;
    }


}