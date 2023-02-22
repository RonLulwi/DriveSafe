package com.example.drivesafe.Entities;

import com.example.drivesafe.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlcoholTest {

    //private int iconId;
    private boolean result;
    private String date;
    //private String info;

    public AlcoholTest(){}

    public AlcoholTest(boolean result){
        Date aDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        this.date = formatter.format(aDate);
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public AlcoholTest setResult(boolean passed) {
        result = passed;
        return this;
    }

    public String getDate() {
        return date;
    }

    public AlcoholTest setDate(String date) {
        this.date = date;
        return this;
    }
}
