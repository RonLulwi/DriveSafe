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
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import com.example.drivesafe.R;
import com.example.drivesafe.Entities.User;
import com.google.android.material.textview.MaterialTextView;

public class FragmentHomePage extends Fragment {
    private AutoCompleteTextView homepage_DRD_currentCarLicense, homepage_DRD_time;
    private MaterialTextView homepage_LBL_activationInfo, homepage_LBL_activationSubInfo;
    private ImageButton homepage_BTN_activation;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
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
        homepage_DRD_currentCarLicense = view.findViewById(R.id.homepage_DRD_currentCarLicense);
        homepage_DRD_time = view.findViewById(R.id.homepage_DRD_time);
        homepage_LBL_activationInfo = view.findViewById(R.id.homepage_LBL_activationInfo);
        homepage_LBL_activationSubInfo = view.findViewById(R.id.homepage_LBL_activationSubInfo);
        homepage_BTN_activation = view.findViewById(R.id.homepage_BTN_activation);
    }
    private void initViews(User user) {
        updateUI(user);
    }


    private void updateUI(User user) {
    }
}