package com.GMH.digital.BarberPub.by.GMH.entities;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceCreateDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "services")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String priceType;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private int durationMinutes;

    private boolean isAvailable;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    public Service() {
    }

    public Service(ServiceCreateDTO serviceCreateDTO) {
        this.name = serviceCreateDTO.getName();
        this.description = serviceCreateDTO.getDescription();
        this.priceType = serviceCreateDTO.getPriceType();
        this.price = BigDecimal.valueOf(serviceCreateDTO.getPrice());
        this.durationMinutes = serviceCreateDTO.getDurationMinutes();
    }

    public Service(ServiceDto service) {
        name = service.getName();
        description = service.getDescription();
        priceType = service.getPriceType();
        price = service.getPrice();
        durationMinutes = service.getDurationMinutes();
        isAvailable = service.getAvailable();
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public long getBusinessId() {
        return business.getId();
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
