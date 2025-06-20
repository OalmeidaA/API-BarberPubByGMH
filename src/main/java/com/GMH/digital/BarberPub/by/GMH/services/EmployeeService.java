package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.EmployeeDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessRespository;

@Service
public class EmployeeService {

	private static final EmployeeDTO EmployeeDTO = null;
	@Autowired
	private EmployeeRespository barberRepository;
	
	@Autowired
	private BusinessRespository barbershopRepository;
	
	@Transactional(readOnly = true)
	public List<Employee> findAllBarber() {
		List<Employee> list = barberRepository.findAll();
		return list;
	}

	@Transactional
	public Employee insertBarber(EmployeeDTO barber) {
		Employee newBarber = new Employee(barber);
		
		Business shop = barbershopRepository.findById(barber.getBarbershopId()).orElseThrow(() -> new RuntimeException("Barbershop not Found"));
		
		barberRepository.save(newBarber);
		return newBarber;
	}

	@Transactional
	public void deleteBarber(Long id) throws Exception {

		if (!barberRepository.existsById(id)) {
			throw new Exception ("ID não encontrado");
		}
		barberRepository.deleteById(id);
	}
	
	@Transactional
	public EmployeeDTO updateBarber(EmployeeDTO dto, Long id) {
		Employee barber = barberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id of Barber not Found"));
		copyBarber(dto, barber); 
		barberRepository.save(barber);
		EmployeeDTO updateBarber = new EmployeeDTO(barber);
		return updateBarber;
	}
	
	public void copyBarber(EmployeeDTO dto, Employee entity) {
		entity.setName(dto.getName());
		entity.setSpecialty(dto.getSpecialty());
	}
	
	
}
