package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.BarberDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Barber;
import com.GMH.digital.BarberPub.by.GMH.entities.Barbershop;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.BarberRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.BarbershopRespository;

@Service
public class BarberService {

	private static final BarberDTO BarberDTO = null;
	@Autowired
	private BarberRespository barberRepository;
	
	@Autowired
	private BarbershopRespository barbershopRepository;
	
	@Transactional(readOnly = true)
	public List<Barber> findAllBarber() {
		List<Barber> list = barberRepository.findAll();
		return list;
	}

	@Transactional
	public Barber insertBarber(BarberDTO barber) {
		Barber newBarber = new Barber(barber);
		
		Barbershop shop = barbershopRepository.findById(barber.getBarbershopId()).orElseThrow(() -> new RuntimeException("Barbershop not Found"));
		
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
	public BarberDTO updateBarber(BarberDTO dto, Long id) {
		Barber barber = barberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id of Barber not Found"));
		copyBarber(dto, barber); 
		barberRepository.save(barber);
		BarberDTO updateBarber = new BarberDTO(barber);
		return updateBarber;
	}
	
	public void copyBarber(BarberDTO dto, Barber entity) {
		entity.setName(dto.getName());
		entity.setSpecialty(dto.getSpecialty());
	}
	
	
}
