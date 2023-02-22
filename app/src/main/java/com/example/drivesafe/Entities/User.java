package com.example.drivesafe.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User implements Parcelable {
    private String firstName, lastName;
    private String address, city;
    private String email, phoneNumber, userPassword;
    private ArrayList<Car> userCar;
    private ArrayList<AlcoholTest> userTests;
    private ArrayList<Dates> userActivationDates;
    private boolean active;

    public User(){}


    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        address = in.readString();
        city = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        userPassword = in.readString();
        active = in.readBoolean();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUserPassword() {
        return userPassword;
    }

    public User setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getFullAddress(){
        return getAddress() + ", " + getCity();
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ArrayList<Car> getUserCar() {
        return userCar;
    }

    public User setUserCar(ArrayList<Car> userCar) {
        this.userCar = userCar;
        return this;
    }

    public ArrayList<AlcoholTest> getUserTests() {
        return userTests;
    }

    public User setUserTests(ArrayList<AlcoholTest> userTests) {
        this.userTests = userTests;
        return this;
    }

    public ArrayList<Dates> getUserActivationDates() {
        return userActivationDates;
    }

    public User setUserActivationDates(ArrayList<Dates> userActivationDates) {
        this.userActivationDates = userActivationDates;
        return this;
    }
    public void addUserActivationDates(Dates dates){
        if(this.userActivationDates == null)
            this.userActivationDates = new ArrayList<>();
        this.userActivationDates.add(dates);
    }

    public boolean getActive() {
        return active;
    }

    public User setActive(boolean active) {
        this.active = active;
        return this;
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCar=" + userCar +
                ", userTests=" + userTests +
                ", userActivationDates=" + userActivationDates +
                ", active=" + active +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.email);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.userPassword);
        dest.writeArray(this.userCar.toArray());
        dest.writeArray(this.userTests.toArray());
        dest.writeArray(this.userActivationDates.toArray());
        dest.writeBoolean(this.active);

    }
}
