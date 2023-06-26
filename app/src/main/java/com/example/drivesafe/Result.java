package com.example.drivesafe;

import com.example.drivesafe.Entities.CarInfoBoundary;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Result {
    @SerializedName("records")
    private List<CarInfoBoundary> cars;

    public List<CarInfoBoundary> getCars() {
        return cars;
    }
    public void setCars(List<CarInfoBoundary> cars) {
        this.cars = cars;
    }
}
