package com.GMH.digital.BarberPub.by.GMH.dto;

import java.util.List;

public class BusinessHoursDTO {

    private List<BusinessHourDTO> businessHours;

    public BusinessHoursDTO() {
    }

    public BusinessHoursDTO(List<BusinessHourDTO> businessHours) {
        this.businessHours = businessHours;
    }

    public List<BusinessHourDTO> getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(List<BusinessHourDTO> businessHours) {
        this.businessHours = businessHours;
    }
}
