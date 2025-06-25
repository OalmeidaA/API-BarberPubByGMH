package com.GMH.digital.BarberPub.by.GMH.entities;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "services")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String duration;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    public Service() {
    }

    public Service(ServiceDTO service) {
        name = service.getName();
        price = service.getPrice();
        duration = service.getDuration();
    }

    public Service(Long id, String name, Integer price, String duration, Business business) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.business = business;
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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Service other = (Service) obj;
        return Objects.equals(id, other.id);
    }
}
