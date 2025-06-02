package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.BarbershopDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Barber;
import com.GMH.digital.BarberPub.by.GMH.entities.Barbershop;
import com.GMH.digital.BarberPub.by.GMH.repositories.BarbershopRespository;

@Service
public class BarbershopService {

	@Autowired
	private BarbershopRespository repository;
	
	@Transactional
	public BarbershopDTO createBarbershop(BarbershopDTO dto){
		Barbershop newBarbershop = new Barbershop(dto);
		repository.save(newBarbershop);
		BarbershopDTO shopDTO = new BarbershopDTO(newBarbershop);
		return shopDTO;
	}
	
	@Transactional(readOnly = true)
	public List<BarbershopDTO> findAllBarbershop(){
		List<Barbershop> list = repository.findAll();
		List<BarbershopDTO> listDto = list.stream().map(x -> new BarbershopDTO(x)).collect(Collectors.toList());
		return listDto;
	}
	
	public Barbershop fromDTO(BarbershopDTO dto) {
		Barbershop barbershop = new Barbershop();
		barbershop.setName(dto.getName());
		barbershop.setEmail(dto.getEmail());
		barbershop.setAddress(dto.getAddress());
		barbershop.setCnpj(dto.getCnpj());
		barbershop.setDescription(dto.getDescription());
		barbershop.setPhone(dto.getPhone());
	
		
		List<Barber> barberList = dto.getBarbers().stream().map(barberDTO -> {
			Barber barber = new Barber();
			barber.setName(barberDTO.getName());
			barber.setSpecialty(barberDTO.getSpecialty());
			barber.setBarbershop(barbershop); 
			return barber;
		}).collect(Collectors.toList());

		barbershop.setBarbers(barberList);

		return barbershop;

	}
}