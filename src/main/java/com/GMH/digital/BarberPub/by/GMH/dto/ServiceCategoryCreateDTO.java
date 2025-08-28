package com.GMH.digital.BarberPub.by.GMH.dto;

public class ServiceCategoryCreateDTO {
    private String name;
    private int sortOrder;

    public ServiceCategoryCreateDTO() {
    }

    public ServiceCategoryCreateDTO(String name, int sortOrder) {
        this.name = name;
        this.sortOrder = sortOrder;
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
}
