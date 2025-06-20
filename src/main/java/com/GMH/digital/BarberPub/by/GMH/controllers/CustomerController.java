package com.GMH.digital.BarberPub.by.GMH.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GMH.digital.BarberPub.by.GMH.dto.BookingDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Booking;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.services.ServiceManager;
import com.GMH.digital.BarberPub.by.GMH.services.CustomerService;

@RestController
@RequestMapping(value = "/users")
@PreAuthorize("hasRole('USER')")
public class CustomerController {

	@Autowired
	private ServiceManager servicesBarberService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<ServiceDTO>> findAllServices(){
		List<Service> list = servicesBarberService.findAllServices();
		List<ServiceDTO> listdto = list.stream().map(x -> new ServiceDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listdto);
	}
	
	@PostMapping("/schedule")
	public ResponseEntity<BookingDTO> scheduleService(@RequestBody BookingDTO dto){
		Booking newScheduling = customerService.scheduleService(dto);
		BookingDTO schedulingDto = new BookingDTO(newScheduling);
		return ResponseEntity.ok(schedulingDto);
	}
	
	
}
