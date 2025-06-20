package com.GMH.digital.BarberPub.by.GMH.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.GMH.digital.BarberPub.by.GMH.entities.Booking;
import com.GMH.digital.BarberPub.by.GMH.entities.Status;


public class BookingDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private LocalDate date;
	private String appointmentHour;
	
	private Long barberId;
	private Long serviceId;
	private Status status;
	
	public BookingDTO() {
		
	}
	
	public BookingDTO(Booking booking) {
		id = booking.getId();
		date = booking.getDate();
		appointmentHour = booking.getAppointmentHour();
		barberId = booking.getBarber().getId();
		serviceId = booking.getService().getId();
		status = booking.getStatus();
	}

	public BookingDTO(Long id, LocalDate date, String appointmentHour, Long barberId, Long serviceId, Status status) {
		super();
		this.id = id;
		this.date = date;
		this.appointmentHour = appointmentHour;
		this.barberId = barberId;
		this.serviceId = serviceId;
		this.status = status; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAppointmentHour() {
		return appointmentHour;
	}

	public void setAppointmentHour(String appointmentHour) {
		this.appointmentHour = appointmentHour;
	}

	public Long getBarberId() {
		return barberId;
	}

	public void setBarberId(Long barberId) {
		this.barberId = barberId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
