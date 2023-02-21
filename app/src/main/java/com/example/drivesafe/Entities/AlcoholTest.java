package com.example.drivesafe.Entities;

import com.example.drivesafe.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlcoholTest {

    private int iconId;
    private boolean isPassed;
    private Date date;
    private String info;

    public AlcoholTest(){}

    public AlcoholTest(boolean isPassed, Date date){
        this.date = date;
        this.isPassed = isPassed;
        if(this.isPassed) {
            this.iconId = R.drawable.ic_passed;
            this.info = "PASSED";
        }else{
            this.iconId = R.drawable.ic_failed;
            this.info = "FAILED";
        }

    }
    public String extractHour(){
        return new SimpleDateFormat("HH:mm").format(this.date);
    }
    public String extractDayAndMonth(){
        return new SimpleDateFormat("d:m").format(this.date);
    }
    public int getIconId() {
        return iconId;
    }

    public AlcoholTest setIconId(int iconId) {
        this.iconId = iconId;
        return this;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public AlcoholTest setPassed(boolean passed) {
        isPassed = passed;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public AlcoholTest setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public AlcoholTest setInfo(String info) {
        this.info = info;
        return this;
    }

    @Override
    public String toString() {
        return "AlcoholTest{" +
                "iconId=" + iconId +
                ", isPassed=" + isPassed +
                ", date=" + date +
                ", info='" + info + '\'' +
                '}';
    }
}
