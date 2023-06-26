package com.example.drivesafe.Entities;

public class Car {
    private String licenseNumber;
    private String manufacture;
    private String model;
    private int manufactureYear;
    private SystemStates systemState;

    public Car() {}

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public SystemStates getSystemState() {
        return systemState;
    }

    public void setSystemState(SystemStates systemState) {
        this.systemState = systemState;
    }
    public String getFullCarInfo(){
        return getManufactureYear()+", " + getManufacture() +" "+ getModel();
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", model='" + model + '\'' +
                ", manufactureYear=" + manufactureYear +
                ", systemStates=" + systemState +
                '}';
    }

}
