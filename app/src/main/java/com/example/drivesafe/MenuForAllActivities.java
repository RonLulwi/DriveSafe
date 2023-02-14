package com.example.drivesafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

public class MenuForAllActivities extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_notification:
                Toast.makeText(this, "clicked notification", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }



    public void showPopupMenu(View view, int itemToRemove){
        PopupMenu popupMenu = new PopupMenu(this,view, Gravity.START, 0,R.style.popupBGStyle );
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setForceShowIcon(true);
        popupMenu.setGravity(Gravity.START);
        Menu menu = popupMenu.getMenu();
        menu.removeItem(itemToRemove);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(item -> onPopupMenuClick(item));
    }

    private boolean onPopupMenuClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popup_menu_profile:
                goToAnotherActivity(MyAccountActivity.class);
                break;
            case R.id.popup_menu_scheduler:
                goToAnotherActivity(SchedulerActivity.class);
                break;
            case R.id.popup_menu_alcoholTests:
                goToAnotherActivity(TestHistoryActivity.class);
                break;
            case R.id.popup_menu_logout:
                goToAnotherActivity(LoginActivity.class);
                break;
            case R.id.popup_menu_home:
                goToAnotherActivity(HomePageActivity.class);
                break;
        }
        return true;
    }

    private void moveTo(String str) {
        Toast.makeText(this, "move to " + str, Toast.LENGTH_SHORT).show();

    }

    private void goToAnotherActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

}
