package com.GMH.digital.BarberPub.by.GMH.dto;

import java.io.Serializable;

public class UserRegisterDTO implements Serializable {
    private String firebaseUid;
    private String email;

    // "customer" ou "employee"
    private String role;

    private String name;
    private String countryCode;
    private String phoneNumber;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(
            String firebaseUid, String email, String role, String name, String countryCode, String phoneNumber) {
        this.firebaseUid = firebaseUid;
        this.email = email;
        this.role = role;
        this.name = name;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
