package com.example.drivesafe.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.drivesafe.Entities.Car;
import com.example.drivesafe.R;
import com.example.drivesafe.Entities.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;


public class FragmentProfile extends Fragment {

    private MaterialTextView profile_LBL_fullName, profile_LBL_address, profile_LBL_email, profile_LBL_carInfo, profile_LBL_password;
    private MaterialButton profile_BTN_password;
    private boolean isVisible = false;


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MainActivity.UPDATE_UI)) {
                User user = intent.getParcelableExtra("user");
                updateUI(user);
            }
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        User user = getArguments().getParcelable("user");
        findViews(view);
        initViews(user);
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
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver);
    }


    private void findViews(View view) {
        profile_LBL_fullName = view.findViewById(R.id.profile_LBL_fullName);
        profile_LBL_address = view.findViewById(R.id.profile_LBL_address);
        profile_LBL_email = view.findViewById(R.id.profile_LBL_email);
        profile_LBL_carInfo = view.findViewById(R.id.profile_LBL_carInfo);
        profile_LBL_password = view.findViewById(R.id.profile_LBL_password);
        profile_BTN_password = view.findViewById(R.id.profile_BTN_password);
    }

    private void initViews(User user) {
        updateUI(user);
        profile_BTN_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVisible){
                    profile_LBL_password.setTransformationMethod(new PasswordTransformationMethod());
                    profile_BTN_password.setText("Show Password");
                    isVisible = false;
                }else{
                    Log.d("pttt", "" + profile_LBL_password.getInputType());
                    profile_LBL_password.setTransformationMethod(null);
                    profile_BTN_password.setText("Hide Password");
                    isVisible = true;
                }

            }
        });
    }
    private void updateUI(User user) {
        if(!profile_LBL_fullName.getText().equals(user.getFullName()))
            profile_LBL_fullName.setText(user.getFullName());
        if(!profile_LBL_address.getText().equals(user.getFullAddress()))
            profile_LBL_address.setText(user.getFullAddress());
        if(!profile_LBL_email.getText().equals(user.getEmail()))
            profile_LBL_email.setText(user.getEmail());
        if(!profile_LBL_password.getText().equals(user.getUserPassword()))
            profile_LBL_password.setText(user.getUserPassword());
        Car c = user.getUserCar().get(0);
        if(!profile_LBL_carInfo.getText().equals(c.getFullCarInfo()))
            profile_LBL_carInfo.setText(c.getFullCarInfo());
    }
}