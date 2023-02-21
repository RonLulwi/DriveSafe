package com.example.drivesafe;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.drivesafe.Entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ServiceUserInfo extends Service {

    private static final String FM_info = "FM_info";
    private static final String ServiceUserInfo_ACTION_START = "ServiceUserInfo_ACTION_START";
    private static final String ServiceUserInfo_ACTION_STOP = "ServiceUserInfo_ACTION_STOP";
    private static final String UPDATE_PROGRESS = "UPDATE_PROGRESS";
    FirebaseUser user;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO listing to Firebase -> if there is a change call updateUI
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User userInfo = dataSnapshot.getValue(User.class);
                    updateUI(userInfo);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                }
            };
            //mPostReference.addValueEventListener(postListener);
        }


        return START_STICKY;
    }

    private void updateUI(User userInfo) {
        Intent intent = new Intent(FM_info);
        intent.putExtra(UPDATE_PROGRESS, 1);
        sendBroadcast(intent);
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
