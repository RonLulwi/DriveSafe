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


public class FragmentsSignupPage03 extends Fragment {

    private AppCompatEditText signup03_EDT_email, signup03_EDT_password, signup03_EDT_confirmPassword, signup03_EDT_id;
    private String email, password, confirmPassword, id;
    private SignUpManager signUpManager;

    private CallBack_SignupProtocol callBack_signupProtocol;

    public void setCallBack_SignupProtocol(CallBack_SignupProtocol callBack_signupProtocol){
        this.callBack_signupProtocol = callBack_signupProtocol;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragments_signup_page_03, container, false);
        findViews(view);
        signUpManager = new SignUpManager();
        return view;
    }

    private void findViews(View view) {
        signup03_EDT_email = view.findViewById(R.id.signup03_EDT_email);
        signup03_EDT_password = view.findViewById(R.id.signup03_EDT_password);
        signup03_EDT_confirmPassword = view.findViewById(R.id.signup03_EDT_confirmPassword);
        signup03_EDT_id = view.findViewById(R.id.signup03_EDT_id);
    }

    public void prepareValues(){
        this.email = signup03_EDT_email.getText().toString();
        this.password = signup03_EDT_password.getText().toString();
        this.confirmPassword = signup03_EDT_confirmPassword.getText().toString();
        this.id = signup03_EDT_id.getText().toString();
    }

    public boolean isInputValid(){
        prepareValues();
        if(this.email.isEmpty() || this.password.isEmpty() || this.confirmPassword.isEmpty() || this.id.isEmpty()){
            signUpManager.showToast("Please enter All missing values");
            return false;
        } else if (!signUpManager.validateEmail(this.email)) {
            signUpManager.showToast("Invalid Email address");
            return false;
        } else if(!signUpManager.validatePassword(this.password, this.confirmPassword)){
            signUpManager.showToast("The password confirmation does not match");
            return false;
        } else if(!signUpManager.validateID(this.id)){
            signUpManager.showToast("Invalid Id");
            return false;
        }
        callBack_signupProtocol.userEmailAndPassword(email, password, id);
        return true;
    }

}