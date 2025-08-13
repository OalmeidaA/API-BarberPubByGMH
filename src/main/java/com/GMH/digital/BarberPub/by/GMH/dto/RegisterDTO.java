package com.GMH.digital.BarberPub.by.GMH.dto;

import java.io.Serializable;

public class RegisterDTO implements Serializable {
    private String firebaseUid;
    private String email;

    // "customer" ou "employee"
    private String role;

    private String name;
    private String phone;

    // apenas se for employee
    private Long businessId;

    public RegisterDTO() {
    }

    public RegisterDTO(String firebaseUid, String email, String role, String name, String phone, Long businessId) {
        this.firebaseUid = firebaseUid;
        this.email = email;
        this.role = role;
        this.name = name;
        this.phone = phone;
        this.businessId = businessId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}
