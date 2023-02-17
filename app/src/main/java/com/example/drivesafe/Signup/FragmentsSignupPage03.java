package com.example.drivesafe.Signup;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drivesafe.R;


public class FragmentsSignupPage03 extends Fragment {

    private AppCompatEditText signup03_EDT_email, signup03_EDT_password, signup03_EDT_confirmPassword;
    private String email, password, confirmPassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragments_signup_page_03, container, false);
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        signup03_EDT_email = view.findViewById(R.id.signup03_EDT_email);
        signup03_EDT_password = view.findViewById(R.id.signup03_EDT_password);
        signup03_EDT_confirmPassword = view.findViewById(R.id.signup03_EDT_confirmPassword);
    }

    public void prepareValues(){
        this.email = signup03_EDT_email.getText().toString();
        this.password = signup03_EDT_password.getText().toString();
        this.confirmPassword = signup03_EDT_confirmPassword.getText().toString();
    }

    public boolean isInputValid(Context context){
        prepareValues();
        if(this.email.isEmpty() || this.password.isEmpty() || this.confirmPassword.isEmpty()){
            Toast.makeText(context, "Please enter All missing values", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(this.email).matches()){
            Toast.makeText(context, "email is not Valid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.equals(confirmPassword)){
            Toast.makeText(context, "The password confirmation does not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}