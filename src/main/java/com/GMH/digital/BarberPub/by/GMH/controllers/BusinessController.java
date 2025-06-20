package com.GMH.digital.BarberPub.by.GMH.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.services.BusinessService;

@RestController
@RequestMapping("/business")
@PreAuthorize("hasRole('ADMIN')")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	
	@PostMapping("/business")
	public ResponseEntity<BusinessDTO> createBarbershop(@RequestBody BusinessDTO dto){
		BusinessDTO newShop = businessService.createBarbershop(dto);
		return ResponseEntity.ok(newShop);
	}
	
	@GetMapping("/business")
	public ResponseEntity<List<BusinessDTO>> findAllBarbershop(){
		List<BusinessDTO> listShop = businessService.findAllBarbershop();
		return ResponseEntity.ok(listShop);
	}

}
