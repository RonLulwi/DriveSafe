package com.example.drivesafe;

import android.app.Application;
import android.content.Context;
import com.example.drivesafe.Utils.MSP;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        MSP.initHelper(this);
    }
    public static Context getAppContext() {
        return App.context;
    }
}
