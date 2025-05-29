package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.ServicesBarberDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.ServicesBarber;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRespository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServicesBarberService {
	
	@Autowired
	private ServiceRespository serviceRespository;
	
	@Transactional(readOnly = true)
	public List<ServicesBarber> findAllServices() {
		List<ServicesBarber> list = serviceRespository.findAll();
		return list;
	}
	
	@Transactional
	public ServicesBarber insertService(ServicesBarberDTO service) {
		ServicesBarber newService = new ServicesBarber(service);
		serviceRespository.save(newService);
		return newService;
	}
	
	@Transactional
	public void deleteService(Long id) throws Exception {
		
		if(!serviceRespository.existsById(id)) {
			throw new Exception("Este Serviço não existe");
		}
		serviceRespository.deleteById(id);
	}
	
	@Transactional
	public ServicesBarberDTO updateServices(Long id, ServicesBarberDTO serviceDto) throws Exception {
		
		try {
		ServicesBarber service = serviceRespository.getReferenceById(id);
		copy(serviceDto, service);
		serviceRespository.save(service);
		return new ServicesBarberDTO(service);
		} 
		catch(EntityNotFoundException e) {
			throw new Exception("Serviço não encontrado para atualização");
		}
	}
	
	public void copy(ServicesBarberDTO dto, ServicesBarber entity) {
		entity.setName(dto.getName());
		entity.setDuration(dto.getDuration());
		entity.setPrice(dto.getPrice());
	}
	
}
