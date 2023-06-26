package com.example.drivesafe.Entities;

import com.google.gson.annotations.SerializedName;

public class CarInfoBoundary {
    @SerializedName("mispar_rechev")
    private String licensePlateNumber;

    @SerializedName("tozeret_nm")
    private String manufacture;

    @SerializedName("shnat_yitzur")
    private String manufactureYear;

    @SerializedName("sug_degem")
    private String carModel;


    public CarInfoBoundary() {
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(int licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber+"";
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    public String getFullCarInfo(){
        return getManufactureYear()+", " + getManufacture() +" "+ getCarModel();
    }

    @Override
    public String toString() {
        return "Car2{" +
                "licensePlateNumber=" + licensePlateNumber +
                ", manufacture='" + manufacture + '\'' +
                ", manufactureYear='" + manufactureYear + '\'' +
                ", carModel='" + carModel + '\'' +
                '}';
    }
}
