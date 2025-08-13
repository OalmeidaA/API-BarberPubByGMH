package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Employee;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {
    private Long id;
    private String name;
    private String specialty;
    private Long businessId;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String specialty, Long id, Long businessId) {
        super();
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.businessId = businessId;
    }

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        specialty = employee.getSpecialty();

        if (employee.getBusiness() != null) {
            businessId = employee.getBusiness().getId();
        }
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}
