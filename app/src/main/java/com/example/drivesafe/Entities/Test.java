package com.example.drivesafe.Entities;

public class Test {

    int image;
    String info, time, date;

    public Test(){}
    public Test(int image, String info, String time, String date) {
        this.image = image;
        this.info = info;
        this.time = time;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public Test setImage(int image) {
        this.image = image;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public Test setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Test setTime(String time) {
        this.time = time;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Test setDate(String date) {
        this.date = date;
        return this;
    }
}
