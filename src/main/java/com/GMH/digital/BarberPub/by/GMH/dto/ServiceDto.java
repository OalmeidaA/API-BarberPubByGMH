package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public class ServiceDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String priceType;
    private BigDecimal price;
    private int durationMinutes;
    private Boolean isAvailable;
    private Instant createdAt;
    private Long businessId;

    public ServiceDto() {
    }

    public ServiceDto(Service service) {
        id = service.getId();
        name = service.getName();
        description = service.getDescription();
        priceType = service.getPriceType();
        price = service.getPrice();
        durationMinutes = service.getDurationMinutes();
        priceType = service.getPriceType();
        isAvailable = service.isAvailable();
        createdAt = service.getCreatedAt();
        businessId = service.getBusiness().getId();
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

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
