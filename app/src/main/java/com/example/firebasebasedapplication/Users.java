package com.example.firebasebasedapplication;

public class Users {
    private String firstName;
    private String LastName;
    private String phoneNumber;

    public Users(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        LastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Users(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Users{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
