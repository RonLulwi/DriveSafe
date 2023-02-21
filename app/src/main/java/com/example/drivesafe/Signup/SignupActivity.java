package com.example.drivesafe.Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.example.drivesafe.Entities.Car;
import com.example.drivesafe.Entities.Dates;
import com.example.drivesafe.LoginActivity;
import com.example.drivesafe.R;
import com.example.drivesafe.Entities.Test;
import com.example.drivesafe.Entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private MaterialButton signup_BTN_next, signup_BTN_back;
    private FragmentsSignupPage01 fragmentsSignupPage01;
    private FragmentsSignupPage02 fragmentsSignupPage02;
    private FragmentsSignupPage03 fragmentsSignupPage03;
    private int currentPage;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private User newUser;


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
        currentPage = 0;
        newUser = new User();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getSupportFragmentManager().beginTransaction().add(R.id.signup_FLY_frame, fragmentsSignupPage01).commit();

        signup_BTN_next.setOnClickListener(view -> {
           switch (currentPage){
               case 0:
                   if(fragmentsSignupPage01.isInputValid(this)) {
                       getSupportFragmentManager().beginTransaction().replace(R.id.signup_FLY_frame, fragmentsSignupPage02).commit();
                       currentPage++;
                   }
                   break;
               case 1:
                   if(fragmentsSignupPage02.isInputValid(this)) {
                       getSupportFragmentManager().beginTransaction().replace(R.id.signup_FLY_frame, fragmentsSignupPage03).commit();
                       signup_BTN_next.setText("Finish");
                       currentPage++;
                   }
                   break;
               case 2:
                   if(fragmentsSignupPage03.isInputValid(this)) {
                       buildUser();
                       signUp(newUser.getEmail(), newUser.getUserPassword());
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

    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            addUserToDB(user);
                            mAuth.signOut();
                            goToAnotherActivity(LoginActivity.class);
                        } else {
                            Toast.makeText(SignupActivity.this, "Authentication failed, Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addUserToDB( FirebaseUser user) {

        mDatabase.child("users").child(user.getUid()).setValue(this.newUser);
    }

    private void buildUser() {
        Car car = new Car()
                .setCarModel(fragmentsSignupPage02.getUserCarModel()).setLicensePlateNumber(fragmentsSignupPage02.getUserLicenseNumber())
                .setManufacture(fragmentsSignupPage02.getUserManufacture()).setManufactureYear(Integer.parseInt(fragmentsSignupPage02.getUserManufactureYear()));
        ArrayList<Car> userCar = new ArrayList<>();
        ArrayList<Test> userTests = new ArrayList<>();
        ArrayList<Dates> userDates = new ArrayList<>();
        userCar.add(car);
        userTests.add(new Test(R.drawable.ic_passed, "PASSED", "22:00", "23/06"));
        this.newUser = new User()
                .setFirstName(fragmentsSignupPage01.getFirstName())
                .setLastName(fragmentsSignupPage01.getLastName())
                .setAddress(fragmentsSignupPage01.getAddress())
                .setCity(fragmentsSignupPage01.getCity())
                .setEmail(fragmentsSignupPage03.getEmail())
                .setUserPassword(fragmentsSignupPage03.getPassword())
                .setUserCar(userCar)
                .setUserTests(userTests)
                .setUserActivationDates(userDates);
    }


    private void goToAnotherActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}