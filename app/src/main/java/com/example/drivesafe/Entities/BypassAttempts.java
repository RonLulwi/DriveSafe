package com.example.drivesafe.Entities;

import java.util.Date;

public class BypassAttempts {

    private String bypassId;
    private String carLicenseNumber;
    private Date timestamp;
    private String testPerformer;
    private String driver;

    public BypassAttempts() {}

    public String getBypassId() {
        return bypassId;
    }

    public void setBypassId(String bypassId) {
        this.bypassId = bypassId;
    }

    public String getCarLicenseNumber() {
        return carLicenseNumber;
    }

    public void setCarLicenseNumber(String carLicenseNumber) {
        this.carLicenseNumber = carLicenseNumber;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTestPerformer() {
        return testPerformer;
    }

    public void setTestPerformer(String testPerformer) {
        this.testPerformer = testPerformer;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "BypassAttempts{" +
                "bypassId='" + bypassId + '\'' +
                ", carLicenseNumber='" + carLicenseNumber + '\'' +
                ", timestamp=" + timestamp +
                ", testPerformer='" + testPerformer + '\'' +
                ", driver='" + driver + '\'' +
                '}';
    }
}
