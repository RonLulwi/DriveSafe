package com.example.drivesafe.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivesafe.LoginActivity;
import com.example.drivesafe.R;
import com.example.drivesafe.Entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageButton main_BTN_notification, main_BTN_popupMenu;
    private TextView main_LBL_pageTitle;
    private Fragment fragmentAlcoholTest, fragmentProfile, fragmentHomePage, fragmentScheduler;
    private FirebaseAuth firebaseAuth;
    private User currUser;
    public static final String UPDATE_UI = "UPDATE_UI";
    public static final String USER = "user";
    private boolean wakeUp = true;

    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            User firebaseUpdatedUser = dataSnapshot.getValue(User.class);
            setCurrUser(firebaseUpdatedUser);
            updateUI(firebaseUpdatedUser);
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
    }

    private void findViews() {
        main_BTN_notification = findViewById(R.id.main_BTN_notification);
        main_BTN_popupMenu = findViewById(R.id.main_BTN_popupMenu);
        main_LBL_pageTitle = findViewById(R.id.main_LBL_pageTitle);
    }

    private void initViews() {
        // init fragments
        fragmentAlcoholTest = new FragmentAlcoholTests();
        fragmentProfile = new FragmentProfile();
        fragmentHomePage = new FragmentHomePage();
        fragmentScheduler = new FragmentScheduler();
        main_BTN_popupMenu.setOnClickListener(v -> showPopupMenu(v));
        main_BTN_notification.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Notifications will be available at the next version", Toast.LENGTH_SHORT).show());
        // get loge-din user
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null) {
            DatabaseReference mUserReference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());
            mUserReference.addValueEventListener(postListener);
        }

    }

    private void updateUI(User user) {
        if (wakeUp){
            wakeUp =false;
            loadFragment(fragmentHomePage, "DRIVE SAFE");
        }
        Intent intent = new Intent(UPDATE_UI);
        intent.putExtra("user", user);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view, Gravity.START, 0,R.style.popupBGStyle );
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setForceShowIcon(true);
        popupMenu.setGravity(Gravity.START);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(item -> onPopupMenuClick(item));
    }

    @SuppressLint("NonConstantResourceId")
    private boolean onPopupMenuClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popup_menu_profile:
                loadFragment(fragmentProfile, "MY ACCOUNT");
                break;
            case R.id.popup_menu_scheduler:
                loadFragment(fragmentScheduler, "SCHEDULER");
                break;
            case R.id.popup_menu_alcoholTests:
                loadFragment(fragmentAlcoholTest, "HISTORY");
                break;
            case R.id.popup_menu_logout:
                firebaseAuth.signOut();
                goToAnotherActivity(LoginActivity.class);
                break;
            case R.id.popup_menu_home:
                loadFragment(fragmentHomePage, "DRIVE SAFE");
                break;
        }
        return true;
    }

    public void loadFragment(Fragment fragment, String title){
        main_LBL_pageTitle.setText(title);
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", currUser);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_FLY_context, fragment).commit();
    }

    private void goToAnotherActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}