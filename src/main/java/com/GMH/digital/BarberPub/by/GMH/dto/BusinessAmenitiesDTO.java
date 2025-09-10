package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Amenity;

import java.util.Set;

public class BusinessAmenitiesDTO {

    private Set<Amenity> amenities;

    public BusinessAmenitiesDTO() {
    }

    public BusinessAmenitiesDTO(Set<Amenity> amenities) {
        this.amenities = amenities;
    }

    public Set<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenity> amenities) {
        this.amenities = amenities;
    }
}
