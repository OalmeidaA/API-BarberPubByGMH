package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerDTO implements Serializable {
    private Long id;
    private String name;
    private String image;
    private String countryCode;
    private String phoneNumber;
    private LocalDate birthDate;
    private String internalNote;
    private String email;
    private Long createdAt;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer user) {
        id = user.getId();
        name = user.getName();
        image = user.getImage();
        countryCode = user.getCountryCode();
        phoneNumber = user.getPhoneNumber();
        internalNote = user.getInternalNote();
        birthDate = user.getBirthDate();
        email = user.getEmail();
        createdAt = user.getCreatedAt() != null ? user.getCreatedAt().toEpochMilli() : null;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getInternalNote() {
        return internalNote;
    }

    public void setInternalNote(String internalNote) {
        this.internalNote = internalNote;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
