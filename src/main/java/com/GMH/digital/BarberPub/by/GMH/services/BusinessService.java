package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessRespository;

@Service
public class BusinessService {

	@Autowired
	private BusinessRespository repository;
	
	@Transactional
	public BusinessDTO createBarbershop(BusinessDTO dto){
		Business newBarbershop = new Business(dto);
		repository.save(newBarbershop);
		BusinessDTO shopDTO = new BusinessDTO(newBarbershop);
		return shopDTO;
	}
	
	@Transactional(readOnly = true)
	public List<BusinessDTO> findAllBarbershop(){
		List<Business> list = repository.findAll();
		List<BusinessDTO> listDto = list.stream().map(x -> new BusinessDTO(x)).collect(Collectors.toList());
		return listDto;
	}
	
	public Business fromDTO(BusinessDTO dto) {
		Business barbershop = new Business();
		barbershop.setName(dto.getName());
		barbershop.setEmail(dto.getEmail());
		barbershop.setAddress(dto.getAddress());
		barbershop.setCnpj(dto.getCnpj());
		barbershop.setDescription(dto.getDescription());
		barbershop.setPhone(dto.getPhone());
	
		
		List<Employee> barberList = dto.getBarbers().stream().map(barberDTO -> {
			Employee barber = new Employee();
			barber.setName(barberDTO.getName());
			barber.setSpecialty(barberDTO.getSpecialty());
			barber.setBarbershop(barbershop); 
			return barber;
		}).collect(Collectors.toList());

		barbershop.setBarbers(barberList);

		return barbershop;

	}
}