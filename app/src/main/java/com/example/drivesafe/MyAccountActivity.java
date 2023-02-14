package com.example.drivesafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class MyAccountActivity extends MenuForAllActivities {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_account);

        findViews();
        initViews();
    }
    private void findViews() {
        toolbar = findViewById(R.id.appbar);
    }
    private void initViews() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> showPopupMenu(view,R.id.popup_menu_profile));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.popup_menu_profile);
        return super.onPrepareOptionsMenu(menu);
    }
}