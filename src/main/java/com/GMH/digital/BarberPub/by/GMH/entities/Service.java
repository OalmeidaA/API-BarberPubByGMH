package com.GMH.digital.BarberPub.by.GMH.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_service")
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer price;
	private String duration;
	
	@Column(name = "tb_barbershop_id")
	private Barbershop barbershop;
	
	public Service() {
	}

	public Service(Long id, String name, Integer price, String duration, Barbershop barbershop) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.duration = duration;
		this.barbershop = barbershop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Barbershop getBarbershop() {
		return barbershop;
	}

	public void setBarbershop(Barbershop barbershop) {
		this.barbershop = barbershop;
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
		Service other = (Service) obj;
		return Objects.equals(id, other.id);
	}
}
