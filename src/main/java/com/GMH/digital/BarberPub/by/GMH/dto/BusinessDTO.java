package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Business;

import java.io.Serializable;

public class BusinessDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cnpj;
    private String address;
    private String description;

    public BusinessDTO() {
    }

    public BusinessDTO(Business business) {
        id = business.getId();
        name = business.getName();
        email = business.getEmail();
        phone = business.getPhone();
        cnpj = business.getCnpj();
        address = business.getAddress();
        description = business.getDescription();
    }

    public BusinessDTO(Long id, String name, String email, String phone, String cnpj, String address, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cnpj = cnpj;
        this.address = address;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
