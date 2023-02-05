package com.example.drivesafe.Signup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.drivesafe.R;


public class FragmentsSignupPage02 extends Fragment {

    private AutoCompleteTextView signup_second_DRD_manufacture, signup_second_DRD_manufactureYear, signup_second_DRD_carModel;
    private String[] manufacture, manufactureYear, carModel;

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
        initAdapter(manufacture,  signup_second_DRD_manufacture,  R.array.manufacture);
        initAdapter(manufactureYear,  signup_second_DRD_manufactureYear,  R.array.manufactureYear);
        initAdapter(carModel,  signup_second_DRD_carModel,  R.array.carModel);
    }

    private void findViews(View view) {
        signup_second_DRD_manufacture = view.findViewById(R.id.signup_second_DRD_manufacture);
        signup_second_DRD_manufactureYear = view.findViewById(R.id.signup_second_DRD_manufactureYear);
        signup_second_DRD_carModel = view.findViewById(R.id.signup_second_DRD_carModel);
    }

    private void initViews() {
        initAdapter(manufacture,  signup_second_DRD_manufacture,  R.array.manufacture);
        initAdapter(manufactureYear,  signup_second_DRD_manufactureYear,  R.array.manufactureYear);
        initAdapter(carModel,  signup_second_DRD_carModel,  R.array.carModel);

        signup_second_DRD_manufacture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ptt", signup_second_DRD_manufacture.getText().toString());
            }
        });

        signup_second_DRD_carModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ptt", signup_second_DRD_carModel.getText().toString());
            }
        });

        signup_second_DRD_manufactureYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ptt", signup_second_DRD_manufactureYear.getText().toString());
            }
        });

    }

    private void initAdapter(String[] list, AutoCompleteTextView autoCompleteTextView, int resource) {
        list = getResources().getStringArray(resource);
        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), R.layout.dropdown_item, list);
        autoCompleteTextView.setAdapter(adapter);
    }

}