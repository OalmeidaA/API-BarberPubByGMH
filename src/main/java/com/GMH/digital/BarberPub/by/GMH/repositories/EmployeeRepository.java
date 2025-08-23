package com.GMH.digital.BarberPub.by.GMH.repositories;

import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByBusiness_Id(Long businessId);
}
