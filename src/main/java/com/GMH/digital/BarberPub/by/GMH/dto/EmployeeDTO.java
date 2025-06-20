package com.GMH.digital.BarberPub.by.GMH.dto;

import java.io.Serializable;
import java.util.List;

import com.GMH.digital.BarberPub.by.GMH.entities.Booking;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;

public class EmployeeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String specialty;
	
	private Long barbershopId;
	private List schedulings;
	
	public EmployeeDTO() {
		
	}

	public EmployeeDTO(String name, String specialty, Long id, Long barbershopId, List schedulings) {
		super();
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.barbershopId = barbershopId;
		this.schedulings = schedulings;
	}
	
	public EmployeeDTO(Employee barber) {
		id = barber.getId();
		name = barber.getName();
		specialty = barber.getSpecialty();
		
		if (barber.getBarbershop() != null) {
			barbershopId = barber.getBarbershop().getId();
		}
		
		List<Booking> list = barber.getSchedulings();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBarbershopId() {
		return barbershopId;
	}

	public void setBarbershopId(Long barbershopId) {
		this.barbershopId = barbershopId;
	}

	public List getSchedulings() {
		return schedulings;
	}

	public void setSchedulings(List schedulings) {
		this.schedulings = schedulings;
	}

}
