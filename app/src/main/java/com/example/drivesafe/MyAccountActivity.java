package com.example.drivesafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccountActivity extends MenuForAllActivities {
    private Toolbar toolbar;
    private DatabaseReference mDatabase;
    private MaterialButton myAccount_BTN_password;
    private MaterialTextView myAccount_LBL_fullName, myAccount_LBL_address, myAccount_LBL_email, myAccount_LBL_carInfo, myAccount_LBL_password;

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
        myAccount_LBL_fullName = findViewById(R.id.myAccount_LBL_fullName);
        myAccount_LBL_address = findViewById(R.id.myAccount_LBL_address);
        myAccount_LBL_email = findViewById(R.id.myAccount_LBL_email);
        myAccount_LBL_carInfo = findViewById(R.id.myAccount_LBL_carInfo);
        myAccount_BTN_password = findViewById(R.id.myAccount_BTN_password);
        myAccount_LBL_password = findViewById(R.id.myAccount_LBL_password);

    }
    private void initViews() {
        FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
        readSUser(currUser.getUid());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> showPopupMenu(view,R.id.popup_menu_profile));
        toolbar.setTitle("MY ACCOUNT");
        myAccount_BTN_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAccount_LBL_password.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });
    }

    private void readSUser(String uId) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                updateUI(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyAccountActivity.this, "Failed to read data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateUI(User user) {
        myAccount_LBL_fullName.setText(user.getFirstName() + " " + user.getLastName());
        myAccount_LBL_address.setText(user.getAddress());
        myAccount_LBL_email.setText(user.getEmail());
        Car c = user.getUserCar().get(0);
        myAccount_LBL_carInfo.setText(c.getManufactureYear() + " " + c.getManufacture() +" "+ c.getCarModel());
        myAccount_LBL_password.setText(user.getUserPassword());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.popup_menu_profile);
        return super.onPrepareOptionsMenu(menu);
    }
}