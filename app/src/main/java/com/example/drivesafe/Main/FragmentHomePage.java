package com.example.drivesafe.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.drivesafe.App;
import com.example.drivesafe.DriveSafeController;
import com.example.drivesafe.Entities.SystemStates;
import com.example.drivesafe.Entities.UserEntity;
import com.example.drivesafe.R;
import com.google.android.material.textview.MaterialTextView;

public class FragmentHomePage extends Fragment {
    private AutoCompleteTextView homepage_DRD_currentCarLicense, homepage_DRD_time;
    private MaterialTextView homepage_LBL_activationInfo, homepage_LBL_activationSubInfo;
    private ImageButton homepage_BTN_activation;
    private UserEntity currUser;
    private String[] time;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MainActivity.UPDATE_UI)) {
                UserEntity user = intent.getParcelableExtra("user");
                setCurrUser(user);
                updateUI();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        if(getArguments() != null)
            this.currUser = (UserEntity) getArguments().getSerializable(MainActivity.USER);
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

    public void setCurrUser(UserEntity currUser) {
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
    private void initViews(UserEntity user) {
        homepage_BTN_activation.setOnClickListener(v -> changeSystemState());
        initAdapter(time,  homepage_DRD_time,  R.array.time);

        homepage_DRD_time.setOnItemClickListener((adapterView, view, i, l) -> {
            if(homepage_DRD_time.getText().toString().equals("--")) {
                homepage_LBL_activationSubInfo.setText("");
                return;
            }
            if(user.getCar().getSystemState().equals(SystemStates.ACTIVE))
                homepage_LBL_activationSubInfo.setText("It will automatically be deactivated at " + homepage_DRD_time.getText().toString());
            else
                homepage_LBL_activationSubInfo.setText("It will automatically be activated at " + homepage_DRD_time.getText().toString());
        });
        updateUI();
    }

    private void changeSystemState() {
        SystemStates currState = currUser.getCar().getSystemState();
        SystemStates wantedState;
        if(currState == SystemStates.ACTIVE)
            wantedState = SystemStates.DEACTIVE;
        else
            wantedState = SystemStates.ACTIVE;
        new DriveSafeController().changeSystemStatus(wantedState, currUser.getCar().getLicenseNumber(), new DriveSafeController.CallBack_changeSystemState() {
            @Override
            public void changeSystemState(boolean response) {
                if (!response)
                    Toast.makeText(App.getAppContext(), "Something went Wrong, try Again", Toast.LENGTH_SHORT).show();
                else{
                    currUser.getCar().setSystemState(wantedState);
                    updateUI();
                }
            }
        });

    }
    private void updateUI() {
        homepage_DRD_currentCarLicense.setText(this.currUser.getCar().getLicenseNumber());
        if(this.currUser.getCar().getSystemState().equals(SystemStates.ACTIVE)){
            homepage_BTN_activation.setImageResource(R.drawable.ic_deactivate);
            homepage_LBL_activationInfo.setTextColor(Color.parseColor("#BED0AC"));
            homepage_LBL_activationInfo.setText("System is active");
            if(homepage_DRD_time.getText().toString().equals("Never") || homepage_DRD_time.getText().toString().equals("")) {
                homepage_LBL_activationSubInfo.setText(" ");
            }else
                homepage_LBL_activationSubInfo.setText("It will automatically be deactivated at " + homepage_DRD_time.getText().toString());
        }else{
            homepage_BTN_activation.setImageResource(R.drawable.ic_activate);
            homepage_LBL_activationInfo.setTextColor(Color.parseColor("#BED0AC"));
            homepage_LBL_activationInfo.setText("System is inactive");
            if(homepage_DRD_time.getText().toString().equals("Never") || homepage_DRD_time.getText().toString().equals("")) {
                homepage_LBL_activationSubInfo.setText(" ");
            }else
                homepage_LBL_activationSubInfo.setText(" ");
        }
    }
    private void initAdapter(String[] list, AutoCompleteTextView autoCompleteTextView, int resource) {
        list = getResources().getStringArray(resource);
        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), R.layout.dropdown_item, list);
        autoCompleteTextView.setAdapter(adapter);
    }
}