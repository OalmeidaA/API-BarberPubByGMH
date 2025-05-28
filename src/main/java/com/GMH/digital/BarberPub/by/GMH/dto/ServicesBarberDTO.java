package com.GMH.digital.BarberPub.by.GMH.dto;

import java.io.Serializable;

import com.GMH.digital.BarberPub.by.GMH.entities.ServicesBarber;

public class ServicesBarberDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer price;
	private String duration;
	
	public ServicesBarberDTO() {
		
	}
	
	public ServicesBarberDTO(ServicesBarber servicesBarber) {
		id = servicesBarber.getId();
		name = servicesBarber.getName();
		price = servicesBarber.getPrice();
		duration = servicesBarber.getDuration();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
