package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.ServiceCategory;

import java.time.Instant;

public class ServiceCategoryDTO {
    private Long id;
    private String name;
    private int sortOrder;
    private Long createdAt;

    public ServiceCategoryDTO() {
    }

    public ServiceCategoryDTO(Long id, String name, int sortOrder, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.sortOrder = sortOrder;
        this.createdAt = createdAt.toEpochMilli();
    }

    public ServiceCategoryDTO(ServiceCategory serviceCategory) {
        this.id = serviceCategory.getId();
        this.name = serviceCategory.getName();
        this.sortOrder = serviceCategory.getSortOrder();
        this.createdAt = serviceCategory.getCreatedAt().toEpochMilli();
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

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
