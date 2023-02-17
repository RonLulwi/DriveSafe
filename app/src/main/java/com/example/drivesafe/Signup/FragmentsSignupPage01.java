package com.example.drivesafe.Signup;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drivesafe.R;

public class FragmentsSignupPage01 extends Fragment {

    private AppCompatEditText signup01_EDT_firstName, signup01_EDT_lastName, signup01_EDT_address, signup01_EDT_city;
    private String firstName, lastName, address, city;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragments_signup_page_01, container, false);
        findViews(view);
        return view;
    }


    private void findViews(View view) {
        signup01_EDT_firstName = view.findViewById(R.id.signup01_EDT_firstName);
        signup01_EDT_lastName = view.findViewById(R.id.signup01_EDT_lastName);
        signup01_EDT_address = view.findViewById(R.id.signup01_EDT_address);
        signup01_EDT_city = view.findViewById(R.id.signup01_EDT_city);
    }

    public void prepareValues(){
        this.firstName = signup01_EDT_firstName.getText().toString();
        this.lastName = signup01_EDT_lastName.getText().toString();
        this.address = signup01_EDT_address.getText().toString();
        this.city = signup01_EDT_city.getText().toString();

    }

    public boolean isInputValid(Context context){
        prepareValues();
        if(this.firstName.isEmpty() || this.lastName.isEmpty() || this.address.isEmpty() || this.city.isEmpty()){
            Toast.makeText(context, "Please enter All missing values", Toast.LENGTH_SHORT).show();
            return false;
        }
          return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}