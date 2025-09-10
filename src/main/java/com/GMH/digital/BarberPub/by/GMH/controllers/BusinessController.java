package com.GMH.digital.BarberPub.by.GMH.controllers;

import com.GMH.digital.BarberPub.by.GMH.dto.AddressDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessAmenitiesDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessSocialsDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Amenity;
import com.GMH.digital.BarberPub.by.GMH.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("me")
    public ResponseEntity<BusinessDTO> getMyBusiness() {
        BusinessDTO business = businessService.getMyBusiness();
        return ResponseEntity.ok(business);
    }

    @GetMapping
    public ResponseEntity<List<BusinessDTO>> getBusiness() {
        List<BusinessDTO> business = businessService.findAllBusiness();
        return ResponseEntity.ok(business);
    }

    @PutMapping("/address")
    public ResponseEntity<Void> updateBusinessAddress(@RequestBody AddressDTO addressDTO) {
        businessService.updateBusinessAddress(addressDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/socials")
    public ResponseEntity<Void> updateSocialsLinks(@RequestBody BusinessSocialsDTO socialsDTO) {
        businessService.updateSocialsLinks(socialsDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/amenities")
    public ResponseEntity<Amenity[]> getAllAmenities() {
        return ResponseEntity.ok(Amenity.values());
    }

    @PutMapping("/amenities")
    public ResponseEntity<Void> updateAmenities(@RequestBody BusinessAmenitiesDTO amenitiesDTO) {
        businessService.updateAmenities(amenitiesDTO);
        return ResponseEntity.noContent().build();
    }
}
