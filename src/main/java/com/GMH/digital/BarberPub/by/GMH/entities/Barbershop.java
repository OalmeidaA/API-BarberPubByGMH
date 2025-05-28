package com.GMH.digital.BarberPub.by.GMH.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_barbershop")
public class Barbershop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique = true)
	private String cnpj;
	
	private String address;
	
	@OneToMany(mappedBy = "barbershop")
	private List<User> users;
	
	@OneToMany(mappedBy = "barbershop")
	private List<Barber> barbers;
	
	@OneToMany(mappedBy = "barbershop")
	private List<Service> services;

	public Barbershop() {
	}

	public Barbershop(Long id, String name, String cnpj, String address) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.address = address;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barbershop other = (Barbershop) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(id, other.id);
	}
	
}
