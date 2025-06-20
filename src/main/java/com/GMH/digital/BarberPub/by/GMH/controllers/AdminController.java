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

import com.GMH.digital.BarberPub.by.GMH.dto.EmployeeDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.services.BusinessService;
import com.GMH.digital.BarberPub.by.GMH.services.EmployeeService;
import com.GMH.digital.BarberPub.by.GMH.services.ServiceManager;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	private ServiceManager serviceManager;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private BusinessService businessService;
	
	@PostMapping
	public ResponseEntity<Void> insertService(@RequestBody ServiceDTO serviceDto){
		Service obj = serviceManager.insertService(serviceDto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ServiceDTO>> findAllServices(){
		List<Service> list = serviceManager.findAllServices();
		List<ServiceDTO> listdto = list.stream().map(x -> new ServiceDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listdto);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteService(@PathVariable Long id) throws Exception {
		serviceManager.deleteService(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) throws Exception {
		serviceDTO = serviceManager.updateServices(id, serviceDTO);
		return ResponseEntity.ok(serviceDTO);
	}
	
	@GetMapping("/barber")
	public ResponseEntity<List<EmployeeDTO>> findAllBarber(){
		List<Employee> list = employeeService.findAllBarber();
		List<EmployeeDTO> listDto = list.stream().map(x -> new EmployeeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}
	
	@PostMapping("/barber")
	public ResponseEntity<Void> insertBarber(@RequestBody EmployeeDTO employeeDTO){
		Employee newBarber = employeeService.insertBarber(employeeDTO);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/barber/{id}")
	public ResponseEntity<Void> deleteBarber(@PathVariable Long id) throws Exception{
		employeeService.deleteBarber(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/barber/{id}")
	public ResponseEntity<EmployeeDTO> updateBarber(@RequestBody EmployeeDTO dto, @PathVariable Long id){
		EmployeeDTO update = employeeService.updateBarber(dto, id);
		return ResponseEntity.ok(update);
	}
	
	@PostMapping("/barbershop")
	public ResponseEntity<BusinessDTO> createBarbershop(@RequestBody BusinessDTO dto){
		BusinessDTO newShop = businessService.createBarbershop(dto);
		return ResponseEntity.ok(newShop);
	}
	
	@GetMapping("/barbershop")
	public ResponseEntity<List<BusinessDTO>> findAllBarbershop(){
		List<BusinessDTO> listShop = businessService.findAllBarbershop();
		return ResponseEntity.ok(listShop);
	}
	
}

