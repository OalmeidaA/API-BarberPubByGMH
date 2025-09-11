package com.GMH.digital.BarberPub.by.GMH.entities;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "business_hours")
public class BusinessHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Boolean closed;

    private LocalTime openingTime;

    private LocalTime closingTime;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    public BusinessHour() {
    }

    public BusinessHour(DayOfWeek dayOfWeek, Boolean closed, LocalTime openingTime, LocalTime closingTime, Business business) {
        this.dayOfWeek = dayOfWeek;
        this.closed = closed;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.business = business;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessHour that = (BusinessHour) o;
        return Objects.equals(id, that.id) && dayOfWeek == that.dayOfWeek && Objects.equals(business, that.business);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, business);
    }
}
