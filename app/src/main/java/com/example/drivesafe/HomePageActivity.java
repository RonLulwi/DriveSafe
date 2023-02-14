package com.example.drivesafe;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Window;

public class HomePageActivity extends MenuForAllActivities {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_page);

        findViews();
        initViews();
    }


    private void findViews() {
        toolbar = findViewById(R.id.appbar);
    }
    private void initViews() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> showPopupMenu(view, R.id.popup_menu_home));
    }
}