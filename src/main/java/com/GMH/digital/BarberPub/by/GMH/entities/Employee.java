package com.GMH.digital.BarberPub.by.GMH.entities;

import com.GMH.digital.BarberPub.by.GMH.dto.EmployeeDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialty;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business barbershop;

    @OneToMany(mappedBy = "employee")
    private List<Booking> bookings;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    public Employee() {

    }

    public Employee(EmployeeDTO barber) {
        name = barber.getName();
        specialty = barber.getSpecialty();
    }

    public Employee(Long id, String name, String specialty, Business barbershop) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.barbershop = barbershop;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Business getBarbershop() {
        return barbershop;
    }

    public void setBarbershop(Business barbershop) {
        this.barbershop = barbershop;
    }

    public List<Booking> getSchedulings() {
        return bookings;
    }

    public void setSchedulings(List<Booking> bookings) {
        this.bookings = bookings;
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
