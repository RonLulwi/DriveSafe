package com.example.drivesafe.Entities;

public class Dates {
    String from,until,date;
    boolean active;
    public Dates(){}
    public Dates(String from, String until, String date, boolean active) {
        this.from = from;
        this.until = until;
        this.date = date;
        this.active = active;
    }

    public String getFrom() {
        return from;
    }

    public Dates setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getUntil() {
        return until;
    }

    public Dates setUntil(String until) {
        this.until = until;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Dates setDate(String date) {
        this.date = date;
        return this;
    }

    public boolean getActive() {
        return active;
    }

    public Dates setActive(boolean active) {
        this.active = active;
        return this;
    }
}
