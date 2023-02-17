package com.example.drivesafe;

public class Car {

    private String licensePlateNumber;
    private String manufacture, carModel;
    private int manufactureYear;
    public Car(){}

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public Car setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
        return this;
    }

    public String getManufacture() {
        return manufacture;
    }

    public Car setManufacture(String manufacture) {
        this.manufacture = manufacture;
        return this;
    }

    public String getCarModel() {
        return carModel;
    }

    public Car setCarModel(String carModel) {
        this.carModel = carModel;
        return this;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public Car setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
        return this;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlateNumber='" + licensePlateNumber + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", carModel='" + carModel + '\'' +
                ", manufactureYear=" + manufactureYear +
                '}';
    }
}
