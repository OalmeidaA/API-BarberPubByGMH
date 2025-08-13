package com.GMH.digital.BarberPub.by.GMH.entities;

import com.GMH.digital.BarberPub.by.GMH.dto.EmployeeDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String name;

    private String specialty;

    private String role;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    public Employee() {
    }

    public Employee(EmployeeDTO barber) {
        name = barber.getName();
        specialty = barber.getSpecialty();
    }

    public Employee(Long id, String name, String specialty, Business business) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.business = business;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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
        Employee other = (Employee) obj;
        return Objects.equals(id, other.id);
    }

}
