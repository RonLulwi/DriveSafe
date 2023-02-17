package com.example.drivesafe.Signup;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.drivesafe.R;


public class FragmentsSignupPage02 extends Fragment {

    private AutoCompleteTextView signup02_DRD_manufacture, signup02_DRD_manufactureYear, signup02_DRD_carModel;
    private AppCompatEditText signup02_EDT_carLicenseNumber;
    private String[] manufacture, manufactureYear, carModel;

    private String userLicenseNumber, userManufacture,userManufactureYear, userCarModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragments_signup_page_02, container, false);

        findViews(view);
        initViews();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initAdapter(manufacture,  signup02_DRD_manufacture,  R.array.manufacture);
        initAdapter(manufactureYear,  signup02_DRD_manufactureYear,  R.array.manufactureYear);
        initAdapter(carModel,  signup02_DRD_carModel,  R.array.carModel);
    }

    private void findViews(View view) {
        signup02_EDT_carLicenseNumber = view.findViewById(R.id.signup02_EDT_carLicenseNumber);
        signup02_DRD_manufacture = view.findViewById(R.id.signup02_DRD_manufacture);
        signup02_DRD_manufactureYear = view.findViewById(R.id.signup02_DRD_manufactureYear);
        signup02_DRD_carModel = view.findViewById(R.id.signup02_DRD_carModel);
    }

    private void initViews() {
        initAdapter(manufacture,  signup02_DRD_manufacture,  R.array.manufacture);
        initAdapter(manufactureYear,  signup02_DRD_manufactureYear,  R.array.manufactureYear);
        initAdapter(carModel,  signup02_DRD_carModel,  R.array.carModel);

        signup02_DRD_manufacture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userManufacture = signup02_DRD_manufacture.getText().toString();
            }
        });

        signup02_DRD_carModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userCarModel = signup02_DRD_carModel.getText().toString();
            }
        });

        signup02_DRD_manufactureYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userManufactureYear = signup02_DRD_manufactureYear.getText().toString();
            }
        });

    }

    private void initAdapter(String[] list, AutoCompleteTextView autoCompleteTextView, int resource) {
        list = getResources().getStringArray(resource);
        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), R.layout.dropdown_item, list);
        autoCompleteTextView.setAdapter(adapter);
    }

    private void prepareValues(){
        this.userLicenseNumber = signup02_EDT_carLicenseNumber.getText().toString();
        this.userManufacture = signup02_DRD_manufacture.getText().toString();
        this.userManufactureYear = signup02_DRD_manufactureYear.getText().toString();
        this.userCarModel = signup02_DRD_carModel.getText().toString();
    }
    public boolean isInputValid(Context context){
        prepareValues();
        if(this.userLicenseNumber.isEmpty() || this.userManufacture.isEmpty() || this.userCarModel.isEmpty() || this.userManufactureYear.isEmpty()){
            Toast.makeText(context, "Please enter All missing values", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public String getUserManufacture() {
        return userManufacture;
    }

    public String getUserManufactureYear() {
        return userManufactureYear;
    }

    public String getUserCarModel() {
        return userCarModel;
    }

    public String getUserLicenseNumber() {
        return userLicenseNumber;
    }
}