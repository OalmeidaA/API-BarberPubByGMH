package com.GMH.digital.BarberPub.by.GMH.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.entities.Customer;

import jakarta.persistence.Column;

public class BusinessDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	
	@Column(unique = true)
	private String cnpj;
	private String address;
	private String description;
	
	private List<Customer> users = new ArrayList<>();
	private List<EmployeeDTO> barbers = new ArrayList<>();
	private List<Service> services = new ArrayList<>();
	
	public BusinessDTO() {
	}
	
	public BusinessDTO(Business barbershop) {
		id = barbershop.getId();
		name = barbershop.getName();
		email = barbershop.getEmail();
		phone = barbershop.getPhone();
		cnpj = barbershop.getCnpj();
		address = barbershop.getAddress();
		description = barbershop.getDescription();
		users = barbershop.getUsers();
		barbers = barbershop.getBarbers().stream().map(EmployeeDTO::new).collect(Collectors.toList());
		services = barbershop.getServices();
	}

	public BusinessDTO(Long id, String name, String email, String phone, String cnpj, String address, String description,
			List<Customer> users, List<EmployeeDTO> barbers, List<Service> services) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.cnpj = cnpj;
		this.address = address;
		this.description = description;
		this.users = users;
		this.barbers = barbers;
		this.services = services;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Customer> getUsers() {
		return users;
	}

	public void setUsers(List<Customer> users) {
		this.users = users;
	}

	public List<EmployeeDTO> getBarbers() {
		return barbers;
	}

	public void setBarbers(List<EmployeeDTO> barbers) {
		this.barbers = barbers;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
