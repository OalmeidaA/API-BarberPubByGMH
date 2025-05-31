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

import com.GMH.digital.BarberPub.by.GMH.dto.SchedulingDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServicesBarberDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Scheduling;
import com.GMH.digital.BarberPub.by.GMH.entities.ServicesBarber;
import com.GMH.digital.BarberPub.by.GMH.services.ServicesBarberService;
import com.GMH.digital.BarberPub.by.GMH.services.UserService;

@RestController
@RequestMapping(value = "/users")
@PreAuthorize("hasRole('USER')")
public class UserController {

	@Autowired
	private ServicesBarberService servicesBarberService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<ServicesBarberDTO>> findAllServices(){
		List<ServicesBarber> list = servicesBarberService.findAllServices();
		List<ServicesBarberDTO> listdto = list.stream().map(x -> new ServicesBarberDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listdto);
	}
	
	@PostMapping("/schedule")
	public ResponseEntity<SchedulingDTO> scheduleService(@RequestBody SchedulingDTO dto){
		Scheduling newScheduling = userService.scheduleService(dto);
		SchedulingDTO schedulingDto = new SchedulingDTO(newScheduling);
		return ResponseEntity.ok(schedulingDto);
	}
	
	
}
