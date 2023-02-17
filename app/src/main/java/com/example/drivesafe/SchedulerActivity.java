package com.example.drivesafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class SchedulerActivity extends MenuForAllActivities {
    private RecyclerView recyclerView;
    private TimePicker scheduler_TPR_start, scheduler_TPR_end;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scheduler);
        findViews();
        initViews();

        scheduler_TPR_start = findViewById(R.id.scheduler_TPR_start);
        scheduler_TPR_end = findViewById(R.id.scheduler_TPR_end);

        scheduler_TPR_end.setIs24HourView(true);
        scheduler_TPR_start.setIs24HourView(true);

        recyclerView = findViewById(R.id.scheduler_RSV_view);
        List<Dates> myDates = new ArrayList<>();

        myDates.add(new Dates("22:00", "02:00", "justTXT", true));
        myDates.add(new Dates("22:00", "02:00", "justTXT", true));
        myDates.add(new Dates("25:00", "02:00", "justTXT", true));
        myDates.add(new Dates("22:00", "02:00", "justTXT", false));
        myDates.add(new Dates("02:00", "02:00", "justTXT", false));
        myDates.add(new Dates("42:00", "02:00", "justTXT", true));
        myDates.add(new Dates("22:30", "02:00", "justTXT", false));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MySchedulerAdapter(getApplicationContext(), myDates));
    }
    private void findViews() {
        toolbar = findViewById(R.id.appbar);
    }
    private void initViews() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> showPopupMenu(view, R.id.popup_menu_scheduler));
        toolbar.setTitle("SCHEDULER");
    }

}