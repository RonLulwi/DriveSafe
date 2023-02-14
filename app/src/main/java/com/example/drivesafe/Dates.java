package com.example.drivesafe;

public class Dates {

    String from,until,date;
    boolean isActive;

    public Dates(String from, String until, String date, boolean isActive) {
        this.from = from;
        this.until = until;
        this.date = date;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public Dates setActive(boolean active) {
        isActive = active;
        return this;
    }
}
