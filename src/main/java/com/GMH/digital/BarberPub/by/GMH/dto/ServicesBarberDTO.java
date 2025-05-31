package com.GMH.digital.BarberPub.by.GMH.dto;

import java.io.Serializable;
import java.util.List;

import com.GMH.digital.BarberPub.by.GMH.entities.Barbershop;
import com.GMH.digital.BarberPub.by.GMH.entities.Scheduling;
import com.GMH.digital.BarberPub.by.GMH.entities.ServicesBarber;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class ServicesBarberDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer price;
	private String duration;
	
	@ManyToOne
	@JoinColumn(name = "tb_barbershop_id")
	private Barbershop barbershop;
	
	@OneToMany(mappedBy = "service")
	private List<Scheduling> schedulings;
	
	public ServicesBarberDTO() {
		
	}
	
	public ServicesBarberDTO(ServicesBarber servicesBarber) {
		id = servicesBarber.getId();
		name = servicesBarber.getName();
		price = servicesBarber.getPrice();
		duration = servicesBarber.getDuration();
		barbershop = servicesBarber.getBarbershop();
		List<Scheduling> listScheduling = servicesBarber.getScheduling();
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
