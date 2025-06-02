package com.GMH.digital.BarberPub.by.GMH.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.GMH.digital.BarberPub.by.GMH.dto.BarberDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BarbershopDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_barbershop")
public class Barbershop implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	
	@Column(unique = true)
	private String cnpj;
	private String address;
	
	@Lob
	private String description;
	
	
	@OneToMany(mappedBy = "barbershop")
	private List<User> users;
	
	@OneToMany(mappedBy = "barbershop", fetch = FetchType.EAGER)
	private List<Barber> barbers;
	
	@OneToMany(mappedBy = "barbershop")
	private List<ServicesBarber> services;

	public Barbershop() {
	}

	public Barbershop(Long id, String name, String cnpj, String address, String email, String phone, String description) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.description = description;
	}
	
	public Barbershop(BarbershopDTO dto) {
		id = dto.getId();
		name = dto.getName();
		email = dto.getEmail();
		phone = dto.getPhone();
		cnpj = dto.getCnpj();
		address = dto.getAddress();
		description = dto.getDescription();
		users = dto.getUsers();
		barbers = dto.getBarbers().stream().map(Barber::new).collect(Collectors.toList());
		services = dto.getServices();
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Barber> getBarbers() {
		return barbers;
	}

	public void setBarbers(List<Barber> barbers) {
		this.barbers = barbers;
	}

	public List<ServicesBarber> getServices() {
		return services;
	}

	public void setServices(List<ServicesBarber> services) {
		this.services = services;
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
