package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.BarberDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Barber;
import com.GMH.digital.BarberPub.by.GMH.repositories.BarberRespository;

@Service
public class BarberService {

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
	
	public void copyBarber(BarberDTO dto, Barber entity) {
		dto.setName(entity.getName());
		dto.setId(entity.getId());
		dto.setSpecialty(entity.getSpecialty());
	}
	
	
}
