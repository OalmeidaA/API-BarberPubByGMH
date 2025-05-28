package com.GMH.digital.BarberPub.by.GMH.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.GMH.digital.BarberPub.by.GMH.dto.ServicesBarberDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_service")
public class ServicesBarber implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer price;
	private String duration;
	
	@ManyToOne
	@JoinColumn(name = "tb_barbershop_id")
	private Barbershop barbershop;
	
	@OneToMany(mappedBy = "service")
	private List<Scheduling> schedulings;
	
	public ServicesBarber() {
	}
	
	public ServicesBarber(ServicesBarberDTO service) {
		name = service.getName();
		price = service.getPrice();
		duration = service.getDuration();
	}

	public ServicesBarber(Long id, String name, Integer price, String duration, Barbershop barbershop) {
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
		ServicesBarber other = (ServicesBarber) obj;
		return Objects.equals(id, other.id);
	}
}
