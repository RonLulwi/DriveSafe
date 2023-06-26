package com.example.drivesafe;


import com.example.drivesafe.Entities.Car;

public interface CallBack_SignupProtocol {

    void personalInfo(String firstName, String lastName, String address, String city);
    void carInfo(Car car);
    void userEmailAndPassword(String email, String password, String id);


}
