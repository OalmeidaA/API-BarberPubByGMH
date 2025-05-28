package com.GMH.digital.BarberPub.by.GMH.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GMH.digital.BarberPub.by.GMH.dto.ServicesBarberDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.ServicesBarber;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRespository;

@Service
public class ServicesBarberService {
	
	@Autowired
	private ServiceRespository serviceRespository;
	
	public List<ServicesBarber> findAllServices() {
		List<ServicesBarber> list = serviceRespository.findAll();
		return list;
	}
	
	public ServicesBarber insertService(ServicesBarberDTO service) {
		ServicesBarber newService = new ServicesBarber(service);
		serviceRespository.save(newService);
		return newService;
	}
}
