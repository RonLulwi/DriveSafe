package com.example.drivesafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private ImageButton main_BTN_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();

    }

    private void findViews() {
        main_BTN_menu = findViewById(R.id.main_BTN_menu);
    }

    private void initViews() {
        main_BTN_menu.setOnClickListener(view -> showPopupMenu(view));

    }

    public void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this,view, Gravity.START, 0,R.style.popupBGStyle );
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu, popupMenu.getMenu());
        popupMenu.setForceShowIcon(true);
        popupMenu.setGravity(Gravity.START);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onPopupMenuClick(item);
            }
        });
    }

    private boolean onPopupMenuClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_account:
                moveTo("profile");
                break;
            case R.id.menu_scheduler:
                moveTo("scheduler");
                break;
            case R.id.menu_tests:
                moveTo("tests");
                break;
            case R.id.menu_logout:
                moveTo("logout");
                break;
        }
        return true;
    }

    private void moveTo(String str) {
        Toast.makeText(this, "move to " + str, Toast.LENGTH_SHORT).show();

    }

}