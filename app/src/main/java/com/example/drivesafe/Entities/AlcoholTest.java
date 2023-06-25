package com.example.drivesafe.Entities;

import java.util.Date;

public class AlcoholTest {
    private String testId;
    private String carLicenseNumber;
    private Date testTimestamp;
    private AlcoholTestResult result;

    public AlcoholTest() {}

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getCarLicenseNumber() {
        return carLicenseNumber;
    }

    public void setCarLicenseNumber(String carLicenseNumber) {
        this.carLicenseNumber = carLicenseNumber;
    }

    public Date getTestTimestamp() {
        return testTimestamp;
    }

    public void setTestTimestamp(Date testTimestamp) {
        this.testTimestamp = testTimestamp;
    }

    public AlcoholTestResult getResult() {
        return result;
    }

    public void setResult(AlcoholTestResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AlcoholTestEntity{" +
                "testId='" + testId + '\'' +
                ", carLicenseNumber='" + carLicenseNumber + '\'' +
                ", testTimestamp=" + testTimestamp +
                ", result=" + result +
                '}';
    }
}
