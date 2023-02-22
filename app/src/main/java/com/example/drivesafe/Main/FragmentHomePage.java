package com.example.drivesafe.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import com.example.drivesafe.R;
import com.example.drivesafe.Entities.User;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentHomePage extends Fragment {
    private AutoCompleteTextView homepage_DRD_currentCarLicense, homepage_DRD_time;
    private MaterialTextView homepage_LBL_activationInfo, homepage_LBL_activationSubInfo;
    private ImageButton homepage_BTN_activation;
    private User currUser;
    private String[] time;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MainActivity.UPDATE_UI)) {
                User user = intent.getParcelableExtra("user");
                setCurrUser(user);
                updateUI();
            }
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        this.currUser = getArguments().getParcelable(MainActivity.USER);
        findViews(view);
        initViews(this.currUser);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
                broadcastReceiver, new IntentFilter(MainActivity.UPDATE_UI)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        initAdapter(time,  homepage_DRD_time,  R.array.time);
        updateUI();
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver);
    }

    private void findViews(View view) {
        homepage_DRD_currentCarLicense = view.findViewById(R.id.homepage_DRD_currentCarLicense);
        homepage_DRD_time = view.findViewById(R.id.homepage_DRD_time);
        homepage_LBL_activationInfo = view.findViewById(R.id.homepage_LBL_activationInfo);
        homepage_LBL_activationSubInfo = view.findViewById(R.id.homepage_LBL_activationSubInfo);
        homepage_BTN_activation = view.findViewById(R.id.homepage_BTN_activation);
    }
    private void initViews(User user) {
        homepage_BTN_activation.setOnClickListener(v -> updateFBSystemActivation());
        initAdapter(time,  homepage_DRD_time,  R.array.time);

        homepage_DRD_time.setOnItemClickListener((adapterView, view, i, l) -> {
            if(homepage_DRD_time.getText().toString().equals("Never")) {
                homepage_LBL_activationSubInfo.setText("");
                return;
            }
            if(user.getActive())
                homepage_LBL_activationSubInfo.setText("it will automatically be Deactivated at: " + homepage_DRD_time.getText().toString());
            else
                homepage_LBL_activationSubInfo.setText("it will automatically be Activated at: " + homepage_DRD_time.getText().toString());
        });
        updateUI();
    }

    private void updateFBSystemActivation() {
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(fbUser.getUid()).child("active");
        if(this.currUser.getActive())
            mDatabase.setValue(false);
        else
            mDatabase.setValue(true);
    }
    private void updateUI() {
        homepage_DRD_currentCarLicense.setText(this.currUser.getUserCar().get(0).getLicensePlateNumber());
        if(this.currUser.getActive()){
            homepage_BTN_activation.setImageResource(R.drawable.ic_deactivate);
            homepage_LBL_activationInfo.setText("System Is Active");
            if(homepage_DRD_time.getText().toString().equals("Never") || homepage_DRD_time.getText().toString().equals("")) {
                homepage_LBL_activationSubInfo.setText("");
            }else
                homepage_LBL_activationSubInfo.setText("it will automatically be Deactivated at: " + homepage_DRD_time.getText().toString());
        }else{
            homepage_BTN_activation.setImageResource(R.drawable.ic_activate);
            homepage_LBL_activationInfo.setText("System Is Deactivate");
            if(homepage_DRD_time.getText().toString().equals("Never") || homepage_DRD_time.getText().toString().equals("")) {
                homepage_LBL_activationSubInfo.setText("");
            }else
                homepage_LBL_activationSubInfo.setText("it will automatically be Activated at: " + homepage_DRD_time.getText().toString());
        }
    }
    private void initAdapter(String[] list, AutoCompleteTextView autoCompleteTextView, int resource) {
        list = getResources().getStringArray(resource);
        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), R.layout.dropdown_item, list);
        autoCompleteTextView.setAdapter(adapter);
    }
}