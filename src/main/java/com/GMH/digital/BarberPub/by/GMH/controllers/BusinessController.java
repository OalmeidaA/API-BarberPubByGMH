package com.GMH.digital.BarberPub.by.GMH.controllers;

import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
