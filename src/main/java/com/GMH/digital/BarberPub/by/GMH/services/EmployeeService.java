package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.EmployeeDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BusinessRespository businessRespository;

    @Transactional
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employeeRepository.save(employee);
        } else {
            Employee existingEmployee = employeeRepository.findById(employee.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
            existingEmployee.setName(employee.getName());
            existingEmployee.setSpecialty(employee.getSpecialty());
            employeeRepository.save(existingEmployee);
        }
    }

    @Transactional(readOnly = true)
    public List<Employee> findAllEmployees() {
        List<Employee> list = employeeRepository.findAll();
        return list;
    }

    @Transactional
    public Employee insertEmployee(EmployeeDTO barber) {
        Employee newBarber = new Employee(barber);

        Business shop = businessRespository
                .findById(barber.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Barbershop not Found"));

        employeeRepository.save(newBarber);
        return newBarber;
    }

    @Transactional
    public void deleteEmployee(Long id) throws Exception {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("ID não encontrado");
        }
        employeeRepository.deleteById(id);
    }

    @Transactional
    public EmployeeDTO updateEmployee(EmployeeDTO dto, Long id) {
        Employee barber = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id of Barber not Found"));
        copyEmployee(dto, barber);
        employeeRepository.save(barber);
        EmployeeDTO updateBarber = new EmployeeDTO(barber);
        return updateBarber;
    }

    public void copyEmployee(EmployeeDTO dto, Employee entity) {
        entity.setName(dto.getName());
        entity.setSpecialty(dto.getSpecialty());
    }


}
