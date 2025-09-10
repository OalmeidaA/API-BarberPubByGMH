package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.BusinessCategory;

public class BusinessBasicDTO {

    private String name;
    private String description;
    private String phone;
    private String email;
    private String cnpj;
    private BusinessCategory category;

    public BusinessBasicDTO() {
    }

    public BusinessBasicDTO(String name, String description, String phone, String email, String cnpj, BusinessCategory category) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.cnpj = cnpj;
        this.category = category;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BusinessCategory getCategory() {
        return category;
    }

    public void setCategory(BusinessCategory category) {
        this.category = category;
    }
}
