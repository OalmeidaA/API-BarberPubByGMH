package com.GMH.digital.BarberPub.by.GMH.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.SchedulingDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Barber;
import com.GMH.digital.BarberPub.by.GMH.entities.Scheduling;
import com.GMH.digital.BarberPub.by.GMH.entities.ServicesBarber;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.BarberRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.SchedulingRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRespository;

@Service
public class UserService {
	
	@Autowired
	private SchedulingRespository schedulingRepository;

	@Autowired
	private BarberRespository barberRespository;
	
	@Autowired
	private ServiceRespository serviceRepository;
	
	@Transactional
	public Scheduling scheduleService(SchedulingDTO dto) {
		Barber barber = barberRespository.findById(dto.getBarberId()).orElseThrow(() -> new ResourceNotFoundException("Id of Barber not Found"));
		
		ServicesBarber service = serviceRepository.findById(dto.getServiceId()).orElseThrow(() -> new ResourceNotFoundException("Id of Service not Found"));
		
		Scheduling scheduling = new Scheduling();
		scheduling.setDate(dto.getDate());
		scheduling.setAppointmentHour(dto.getAppointmentHour());
		scheduling.setBarber(barber);
		scheduling.setService(service);
		scheduling.setStatus(dto.getStatus());
	
		return schedulingRepository.save(scheduling);
	}
	

}
