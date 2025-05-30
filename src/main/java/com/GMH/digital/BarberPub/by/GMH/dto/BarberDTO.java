package com.GMH.digital.BarberPub.by.GMH.dto;

import java.io.Serializable;

import com.GMH.digital.BarberPub.by.GMH.entities.Barber;

public class BarberDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String specialty;
	
	public BarberDTO() {
		
	}

	public BarberDTO(String name, String specialty, Long id) {
		super();
		this.id = id;
		this.name = name;
		this.specialty = specialty;
	}
	
	public BarberDTO(Barber barber) {
		id = barber.getId();
		name = barber.getName();
		specialty = barber.getSpecialty();
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
	

}
