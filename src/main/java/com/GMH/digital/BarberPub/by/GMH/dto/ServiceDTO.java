package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Service;

import java.io.Serializable;

public class ServiceDTO implements Serializable {

    private Long id;
    private String name;
    private Integer price;
    private String duration;
    private Long bussinessId;

    public ServiceDTO() {
    }

    public ServiceDTO(Service service) {
        id = service.getId();
        name = service.getName();
        price = service.getPrice();
        duration = service.getDuration();
        bussinessId = service.getBusiness().getId();
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Long bussinessId) {
        this.bussinessId = bussinessId;
    }
}
