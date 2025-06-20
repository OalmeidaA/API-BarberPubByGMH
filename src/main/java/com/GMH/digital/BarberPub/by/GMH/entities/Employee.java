package com.GMH.digital.BarberPub.by.GMH.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.GMH.digital.BarberPub.by.GMH.dto.EmployeeDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_barber")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String specialty;
	
	@ManyToOne
	@JoinColumn(name = "tb_barbershop_id")
	private Business barbershop;
	
	@OneToMany(mappedBy = "barber")
	private List<Booking> bookings;
	
	public Employee() {
		
	}
	
	public Employee(EmployeeDTO barber) {
		name = barber.getName();
		specialty = barber.getSpecialty();
	}

	public Employee(Long id, String name, String specialty, Business barbershop) {
		this.id = id;
		this.name = name;
		this.specialty = specialty;
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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Business getBarbershop() {
		return barbershop;
	}

	public void setBarbershop(Business barbershop) {
		this.barbershop = barbershop;
	}

	public List<Booking> getSchedulings() {
		return bookings;
	}

	public void setSchedulings(List<Booking> bookings) {
		this.bookings = bookings;
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
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}
	
}
