package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.BarberDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Barber;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.BarberRespository;

@Service
public class BarberService {

	private static final BarberDTO BarberDTO = null;
	@Autowired
	private BarberRespository repository;
	
	@Transactional(readOnly = true)
	public List<Barber> findAllBarber() {
		List<Barber> list = repository.findAll();
		return list;
	}

	@Transactional
	public Barber insertBarber(BarberDTO barber) {
		Barber newBarber = new Barber(barber);
		repository.save(newBarber);
		return newBarber;
	}

	@Transactional
	public void deleteBarber(Long id) throws Exception {

		if (!repository.existsById(id)) {
			throw new Exception ("ID não encontrado");
		}
		repository.deleteById(id);
	}
	
	@Transactional
	public BarberDTO updateBarber(BarberDTO dto, Long id) {
		Barber barber = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id of Barber not Found"));
		copyBarber(dto, barber); 
		repository.save(barber);
		BarberDTO updateBarber = new BarberDTO(barber);
		return updateBarber;
	}
	
	public void copyBarber(BarberDTO dto, Barber entity) {
		entity.setName(dto.getName());
		entity.setSpecialty(dto.getSpecialty());
	}
	
	
}
