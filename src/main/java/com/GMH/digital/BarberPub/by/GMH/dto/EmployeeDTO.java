package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Employee;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String countryCode;
    private String phoneNumber;
    private String email;
    private boolean isActive;
    private String role;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String specialty, Long id, Long businessId) {
        super();
        this.id = id;
        this.name = name;
    }

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        description = employee.getDescription();
        countryCode = employee.getCountryCode();
        phoneNumber = employee.getPhoneNumber();
        email = employee.getEmail();
        isActive = employee.isActive();
        role = employee.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
