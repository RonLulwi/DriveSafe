package com.example.drivesafe.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.drivesafe.App;
import com.example.drivesafe.Entities.Dates;
import com.example.drivesafe.Entities.UserEntity;
import com.example.drivesafe.MySchedulerAdapter;
import com.example.drivesafe.R;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class FragmentScheduler extends Fragment {

    private TimePicker scheduler_TPR_start, scheduler_TPR_end;
    private ImageButton scheduler_IMG_calender;
    private MaterialButton scheduler_BTN_save;
    private RecyclerView scheduler_RSV_view;
    private CheckBox scheduler_CBX_sunday, scheduler_CBX_monday, scheduler_CBX_tuesday, scheduler_CBX_wednesday, scheduler_CBX_thursday, scheduler_CBX_friday, scheduler_CBX_saturday;
    List<Dates> myDates;
    private UserEntity currUser;


    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MainActivity.UPDATE_UI)) {
                UserEntity user = (UserEntity)intent.getSerializableExtra("user");
                updateUI(user);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scheduler, container, false);
        UserEntity user = (UserEntity) getArguments().getSerializable(MainActivity.USER);
        findViews(view);
        initViews(user);
        return view;
    }

    private void initViews(UserEntity user) {
        scheduler_TPR_start.setIs24HourView(true);
        scheduler_TPR_end.setIs24HourView(true);
        scheduler_IMG_calender.setOnClickListener(v -> Toast.makeText(getContext(), "Calendar will be available at the next version", Toast.LENGTH_SHORT).show());
        scheduler_CBX_sunday.setOnClickListener(v -> setDayColor(scheduler_CBX_sunday));
        scheduler_CBX_monday.setOnClickListener(v -> setDayColor(scheduler_CBX_monday));
        scheduler_CBX_tuesday.setOnClickListener(v -> setDayColor(scheduler_CBX_tuesday));
        scheduler_CBX_wednesday.setOnClickListener(v -> setDayColor(scheduler_CBX_wednesday));
        scheduler_CBX_thursday.setOnClickListener(v -> setDayColor(scheduler_CBX_thursday));
        scheduler_CBX_friday.setOnClickListener(v -> setDayColor(scheduler_CBX_friday));
        scheduler_CBX_saturday.setOnClickListener(v -> setDayColor(scheduler_CBX_saturday));
        updateUI(user);
    }

    private void setDayColor(CheckBox checkBox){
        if(checkBox.isChecked())
            checkBox.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.my_green));
        else
            checkBox.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.icons_color));
    }
    private void resetDays(){
        if(scheduler_CBX_sunday.isChecked()) {
            scheduler_CBX_sunday.setChecked(false);
            scheduler_CBX_sunday.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.icons_color));
        }if(scheduler_CBX_monday.isChecked()) {
            scheduler_CBX_monday.setChecked(false);
            scheduler_CBX_monday.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.icons_color));
        }if(scheduler_CBX_tuesday.isChecked()) {
            scheduler_CBX_tuesday.setChecked(false);
            scheduler_CBX_tuesday.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.icons_color));
        }if(scheduler_CBX_wednesday.isChecked()) {
            scheduler_CBX_wednesday.setChecked(false);
            scheduler_CBX_wednesday.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.icons_color));
        }if(scheduler_CBX_thursday.isChecked()) {
            scheduler_CBX_thursday.setChecked(false);
            scheduler_CBX_thursday.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.icons_color));
        }if(scheduler_CBX_friday.isChecked()) {
            scheduler_CBX_friday.setChecked(false);
            scheduler_CBX_friday.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.icons_color));
        }if(scheduler_CBX_saturday.isChecked()) {
            scheduler_CBX_saturday.setChecked(false);
            scheduler_CBX_saturday.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.icons_color));
        }
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
        scheduler_TPR_start = view.findViewById(R.id.scheduler_TPR_start);
        scheduler_TPR_end = view.findViewById(R.id.scheduler_TPR_end);
        scheduler_IMG_calender = view.findViewById(R.id.scheduler_IMG_calender);
        scheduler_BTN_save = view.findViewById(R.id.scheduler_BTN_save);
        scheduler_RSV_view = view.findViewById(R.id.scheduler_RSV_view);
        scheduler_CBX_sunday = view.findViewById(R.id.scheduler_CBX_sunday);
        scheduler_CBX_monday = view.findViewById(R.id.scheduler_CBX_monday);
        scheduler_CBX_tuesday = view.findViewById(R.id.scheduler_CBX_tuesday);
        scheduler_CBX_wednesday = view.findViewById(R.id.scheduler_CBX_wednesday);
        scheduler_CBX_thursday = view.findViewById(R.id.scheduler_CBX_thursday);
        scheduler_CBX_friday = view.findViewById(R.id.scheduler_CBX_friday);
        scheduler_CBX_saturday = view.findViewById(R.id.scheduler_CBX_saturday);
    }

    private void updateUI(UserEntity user) {
        this.currUser = user;
        scheduler_RSV_view.setLayoutManager(new LinearLayoutManager(getContext()));
        scheduler_RSV_view.setAdapter(new MySchedulerAdapter(getContext(), myDates));
    }
}