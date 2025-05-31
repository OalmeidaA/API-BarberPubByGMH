package com.GMH.digital.BarberPub.by.GMH.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.GMH.digital.BarberPub.by.GMH.dto.SchedulingDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_scheduling")
public class Scheduling implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private String appointmentHour;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "tb_user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "tb_barber_id")
	private Barber barber;
	
	@ManyToOne
	@JoinColumn(name = "tb_service_id")
	private ServicesBarber service;
	
	public Scheduling() {
		
	}
	
	public Scheduling(SchedulingDTO dto) {	
		id = dto.getId();
		date = dto.getDate();
		appointmentHour = dto.getAppointmentHour();
	}

	public Scheduling(Long id, LocalDate date, String appointmentHour, Status status, User user, Barber barber, ServicesBarber service) {
		this.id = id;
		this.date = date;
		this.appointmentHour = appointmentHour;
		this.status = status;
		this.user = user;
		this.barber = barber;
		this.service = service;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Barber getBarber() {
		return barber;
	}

	public void setBarber(Barber barber) {
		this.barber = barber;
	}

	public ServicesBarber getService() {
		return service;
	}

	public void setService(ServicesBarber service) {
		this.service = service;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scheduling other = (Scheduling) obj;
		return Objects.equals(id, other.id);
	}
	
}
