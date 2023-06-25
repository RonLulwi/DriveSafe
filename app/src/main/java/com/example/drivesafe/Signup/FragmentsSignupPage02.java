package com.example.drivesafe.Signup;

import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.drivesafe.CallBack_SignupProtocol;
import com.example.drivesafe.CarInfoController;
import com.example.drivesafe.Entities.Car;
import com.example.drivesafe.R;
import com.example.drivesafe.SignUpManager;
import com.google.android.material.button.MaterialButton;


public class FragmentsSignupPage02 extends Fragment {

    private AppCompatEditText signup02_EDT_carLicenseNumber, signup02_DRD_manufacture, signup02_DRD_manufactureYear, signup02_DRD_carModel;
    private MaterialButton signup02_BTN_getInfo;
    private String userLicenseNumber, userManufacture,userManufactureYear, userCarModel;
    private Car newCar;
    private CallBack_SignupProtocol callBack_signupProtocol;
    private SignUpManager signUpManager;

    public void setCallBack_SignupProtocol(CallBack_SignupProtocol callBack_signupProtocol){
        this.callBack_signupProtocol = callBack_signupProtocol;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_signup_page_02, container, false);
        findViews(view);
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void findViews(View view) {
        signup02_EDT_carLicenseNumber = view.findViewById(R.id.signup02_EDT_carLicenseNumber);
        signup02_DRD_manufacture = view.findViewById(R.id.signup02_DRD_manufacture);
        signup02_DRD_manufactureYear = view.findViewById(R.id.signup02_DRD_manufactureYear);
        signup02_DRD_carModel = view.findViewById(R.id.signup02_DRD_carModel);
        signup02_BTN_getInfo = view.findViewById(R.id.signup02_BTN_getInfo);
    }

    private void initViews() {
        signUpManager = new SignUpManager();
        signup02_BTN_getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String licenseNumber = signup02_EDT_carLicenseNumber.getText().toString();
                if(!licenseNumber.equals("") && signUpManager.validateLicensePlate(licenseNumber))
                    findCarInfo(licenseNumber);
                else
                    signUpManager.showToast("Invalid License plate number");
            }
        });
    }

    private void findCarInfo(String carLicenseNumber) {
        new CarInfoController().fetchCarInfo(carLicenseNumber, new CarInfoController.Callback_CarInfo() {
            @Override
            public void getCarInfo(Car car) {
                if(car!=null) {
                    setCar(car);
                    updateUI();
                }else
                    signUpManager.showToast("Car with LicenseNumber "+ carLicenseNumber + " not found. Please Try Again!");
            }
        });
    }

    private void updateUI() {
        this.signup02_DRD_manufacture.setText(this.newCar.getManufacture());
        this.signup02_DRD_manufactureYear.setText(this.newCar.getManufactureYear()+"");
        this.signup02_DRD_carModel.setText(this.newCar.getModel());
    }

    public boolean isInputValid() {
        if (this.newCar != null){
            callBack_signupProtocol.carInfo(this.newCar);
            return true;
        }else
            signUpManager.showToast("Invalid License plate number");
        return false;
    }

    private void setCar(Car car){
        this.newCar = car;
    }
}