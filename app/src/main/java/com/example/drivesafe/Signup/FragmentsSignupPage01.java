package com.example.drivesafe.Signup;

import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.drivesafe.CallBack_SignupProtocol;
import com.example.drivesafe.R;
import com.example.drivesafe.SignUpManager;

public class FragmentsSignupPage01 extends Fragment {

    private AppCompatEditText signup01_EDT_firstName, signup01_EDT_lastName, signup01_EDT_address, signup01_EDT_city;
    private String firstName, lastName, address, city;

    private SignUpManager signUpManager;
    private CallBack_SignupProtocol callBack_signupProtocol;

    public void setCallBack_SignupProtocol(CallBack_SignupProtocol callBack_signupProtocol){
        this.callBack_signupProtocol = callBack_signupProtocol;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_signup_page_01, container, false);
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        signup01_EDT_firstName = view.findViewById(R.id.signup01_EDT_firstName);
        signup01_EDT_lastName = view.findViewById(R.id.signup01_EDT_lastName);
        signup01_EDT_address = view.findViewById(R.id.signup01_EDT_address);
        signup01_EDT_city = view.findViewById(R.id.signup01_EDT_city);
        signUpManager = new SignUpManager();
    }

    public boolean isInputValid() {
        prepareValues();
        if (!signUpManager.validateName(this.firstName)) {
            signUpManager.showToast("first Name is invalid");
            return false;
        } else if (!signUpManager.validateName(this.lastName)){
            signUpManager.showToast("last Name is invalid");
            return false;
        }else if (!signUpManager.validateCity(this.city) ) {
            signUpManager.showToast("city is invalid");
            return false;
        }else if (this.address.isEmpty()){
            signUpManager.showToast("address is invalid");
            return false;
        }
        callBack_signupProtocol.personalInfo(this.firstName, this.lastName, this.address, this.city);
        return true;
    }
    public void prepareValues(){
        this.firstName = signup01_EDT_firstName.getText().toString();
        this.lastName = signup01_EDT_lastName.getText().toString();
        this.address = signup01_EDT_address.getText().toString();
        this.city = signup01_EDT_city.getText().toString();

    }

}