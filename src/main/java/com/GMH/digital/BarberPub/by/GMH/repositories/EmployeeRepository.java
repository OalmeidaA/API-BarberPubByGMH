package com.GMH.digital.BarberPub.by.GMH.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GMH.digital.BarberPub.by.GMH.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
