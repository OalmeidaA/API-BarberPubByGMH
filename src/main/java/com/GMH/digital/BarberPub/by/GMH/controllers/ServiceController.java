package com.GMH.digital.BarberPub.by.GMH.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.services.ServiceManager;

@RestController
@RequestMapping("/service")
@PreAuthorize("hasRole('ADMIN')")
public class ServiceController {
	
	@Autowired
	private ServiceManager serviceManager;

	
	@PostMapping
	public ResponseEntity<Void> insertService(@RequestBody ServiceDTO serviceDto){
		Service obj = serviceManager.insertService(serviceDto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteService(@PathVariable Long id) throws Exception {
		serviceManager.deleteService(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) throws Exception {
		serviceDTO = serviceManager.updateService(id, serviceDTO);
		return ResponseEntity.ok(serviceDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ServiceDTO>> findAllServices(){
		List<Service> list = serviceManager.findAllServices();
		List<ServiceDTO> listdto = list.stream().map(x -> new ServiceDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listdto);
	}
	
}
