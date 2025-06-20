package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRespository;

import jakarta.persistence.EntityNotFoundException;

@org.springframework.stereotype.Service
public class ServiceManager {
	
	@Autowired
	private ServiceRespository serviceRespository;
	
	@Transactional(readOnly = true)
	public List<Service> findAllServices() {
		List<Service> list = serviceRespository.findAll();
		return list;
	}
	
	@Transactional
	public Service insertService(ServiceDTO service) {
		Service newService = new Service(service);
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
	public ServiceDTO updateService(Long id, ServiceDTO serviceDto) throws Exception {
		
		try {
		Service service = serviceRespository.getReferenceById(id);
		copy(serviceDto, service);
		serviceRespository.save(service);
		return new ServiceDTO(service);
		} 
		catch(EntityNotFoundException e) {
			throw new Exception("Serviço não encontrado para atualização");
		}
	}
	
	public void copy(ServiceDTO dto, Service entity) {
		entity.setName(dto.getName());
		entity.setDuration(dto.getDuration());
		entity.setPrice(dto.getPrice());
	}
	
}
