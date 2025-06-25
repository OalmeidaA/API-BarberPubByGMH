package com.GMH.digital.BarberPub.by.GMH.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import com.GMH.digital.BarberPub.by.GMH.dto.BookingDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.Booking;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.BookingRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRespository;

@org.springframework.stereotype.Service
public class CustomerService {
	
	@Autowired
	private BookingRespository schedulingRepository;

	@Autowired
	private EmployeeRespository barberRespository;
	
	@Autowired
	private ServiceRespository serviceRepository;
	
	@Transactional
	public Booking scheduleService(BookingDTO dto) {
		Employee barber = barberRespository.findById(dto.getBarberId()).orElseThrow(() -> new ResourceNotFoundException("Id of Barber not Found"));
		
		Service service = serviceRepository.findById(dto.getServiceId()).orElseThrow(() -> new ResourceNotFoundException("Id of Service not Found"));
		
		Booking booking = new Booking();
		booking.setDate(dto.getDate());
		booking.setAppointmentHour(dto.getAppointmentHour());
		booking.setEmployee(barber);
		booking.setService(service);
		booking.setStatus(dto.getStatus());
	
		return schedulingRepository.save(booking);
	}
	

}
