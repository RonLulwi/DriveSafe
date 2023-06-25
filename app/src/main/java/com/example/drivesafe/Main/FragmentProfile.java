package com.example.drivesafe.Main;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.drivesafe.App;
import com.example.drivesafe.DriveSafeController;
import com.example.drivesafe.Entities.UserEntity;
import com.example.drivesafe.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class FragmentProfile extends Fragment {
    private TextInputEditText profile_LBL_fullName, profile_LBL_address, profile_LBL_email, profile_LBL_carInfo, profile_LBL_licenseNumber;
    private MaterialButton profile_BTN_editInfo, profile_BTN_saveInfo;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MainActivity.UPDATE_UI)) {
                UserEntity user = (UserEntity) intent.getSerializableExtra("user");
                updateUI(user);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        UserEntity user = (UserEntity) getArguments().getSerializable("user");
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
        profile_LBL_licenseNumber = view.findViewById(R.id.profile_LBL_licenseNumber);
        profile_BTN_editInfo = view.findViewById(R.id.profile_BTN_editInfo);
        profile_BTN_saveInfo = view.findViewById(R.id.profile_BTN_saveInfo);
        profile_BTN_saveInfo.setVisibility(View.GONE);
    }

    private void initViews(UserEntity user) {
        updateUI(user);
        changeEditStatus(false);

        profile_BTN_saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserInfo(user);
            }
        });
        profile_BTN_editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialog(user);
            }
        });
    }

    private void showDialog(UserEntity user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Drive Safe");
        builder.setMessage("Enter your password:");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("Confirm Password", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String str = input.getText()+"";
                if(str.equals("") || !str.equals(user.getPassword())){
                    Toast.makeText(getContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
                    changeEditStatus(true);
                    profile_BTN_saveInfo.setVisibility(View.VISIBLE);
                    dialog.cancel();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void changeEditStatus(boolean isActive){
        profile_LBL_fullName.setEnabled(isActive);
        profile_LBL_address.setEnabled(isActive);
        profile_LBL_email.setEnabled(isActive);
    }

    private void updateUserInfo(UserEntity user){
            new DriveSafeController().updateUserInfo(user.getId(), user, new DriveSafeController.CallBack_updateUser() {
                @Override
                public void updatedUser(boolean response) {
                    if(response)
                        Toast.makeText(App.getAppContext(), "Success", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(App.getAppContext(), "Failed", Toast.LENGTH_SHORT).show();

                }
            });
        profile_BTN_saveInfo.setVisibility(View.GONE);
        changeEditStatus(false);
    }
    private void updateUI(UserEntity user) {
        profile_LBL_fullName.setText(user.fullName());
        profile_LBL_address.setText(user.fullAddress());
        profile_LBL_email.setText(user.getEmail());
        profile_LBL_carInfo.setText(user.getCar().getFullCarInfo());
        profile_LBL_licenseNumber.setText(user.getCar().getLicenseNumber());
    }
}