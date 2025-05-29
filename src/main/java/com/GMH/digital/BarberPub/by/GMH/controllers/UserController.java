package com.GMH.digital.BarberPub.by.GMH.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GMH.digital.BarberPub.by.GMH.dto.ServicesBarberDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.ServicesBarber;
import com.GMH.digital.BarberPub.by.GMH.services.ServicesBarberService;

@RestController
@RequestMapping(value = "/Client")
public class UserController {

	@Autowired
	private ServicesBarberService servicesBarberService;
	
	@GetMapping
	public ResponseEntity<List<ServicesBarberDTO>> findAllServices(){
		List<ServicesBarber> list = servicesBarberService.findAllServices();
		List<ServicesBarberDTO> listdto = list.stream().map(x -> new ServicesBarberDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listdto);
	}
	
	
}
