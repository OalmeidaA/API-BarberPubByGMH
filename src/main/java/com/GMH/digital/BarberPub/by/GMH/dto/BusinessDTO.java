package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Business;

import java.io.Serializable;
import java.time.Instant;

public class BusinessDTO implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cnpj;
    private AddressDTO address;
    private String description;
    private Instant createdAt;

    public BusinessDTO() {
    }

    public BusinessDTO(Business entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.cnpj = entity.getCnpj();
        this.description = entity.getDescription();
        this.createdAt = entity.getCreatedAt();

        if (entity.getAddress() != null) {
            this.address = new AddressDTO(
                entity.getAddress().getId(),
                entity.getAddress().getStreet(),
                entity.getAddress().getNumber(),
                entity.getAddress().getComplement(),
                entity.getAddress().getNeighborhood(),
                entity.getAddress().getCity(),
                entity.getAddress().getState(),
                entity.getAddress().getPostalCode(),
                entity.getAddress().getCountry()
            );
        }
    }

    // Getters and Setters
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
