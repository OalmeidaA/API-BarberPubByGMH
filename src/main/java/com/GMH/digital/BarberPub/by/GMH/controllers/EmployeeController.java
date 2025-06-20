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
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.services.EmployeeService;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
 
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
	
	@GetMapping("/barber")
	public ResponseEntity<List<EmployeeDTO>> findAllBarber(){
		List<Employee> list = employeeService.findAllBarber();
		List<EmployeeDTO> listDto = list.stream().map(x -> new EmployeeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}
}
