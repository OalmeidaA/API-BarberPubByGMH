package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.EmployeeCreateDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.EmployeeDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.User;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private final AuthenticatedUserService authenticatedUserService;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(
            AuthenticatedUserService authenticatedUserService, EmployeeRepository employeeRepository) {
        this.authenticatedUserService = authenticatedUserService;
        this.employeeRepository = employeeRepository;
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public List<EmployeeDTO> getEmployees() {
        Long businessId = getBusinessId();
        return employeeRepository.findByBusiness_Id(businessId)
                .stream()
                .map(EmployeeDTO::new)
                .toList();
    }

    public void createEmployee(EmployeeCreateDTO dto) {
        Employee currentEmployee = getCurrentEmployee();
        Business business = currentEmployee.getBusiness();

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDescription(dto.getDescription());
        employee.setCountryCode(dto.getCountryCode());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setEmail(dto.getEmail());
        employee.setRole(dto.getRole());
        employee.setBusiness(business);

        employeeRepository.save(employee);
    }

    public void updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = findById(id);

        Employee currentEmployee = getCurrentEmployee();
        if (!Objects.equals(employee.getBusinessId(), currentEmployee.getBusinessId())) {
            throw new AccessDeniedException("Employee does not belong to your business");
        }

        employee.setName(dto.getName());
        employee.setDescription(dto.getDescription());
        employee.setCountryCode(dto.getCountryCode());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setEmail(dto.getEmail());
        employee.setActive(dto.isActive());

        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = findById(id);
        if (!Objects.equals(employee.getBusinessId(), getCurrentEmployee().getBusinessId())) {
            throw new AccessDeniedException("Employee does not belong to your business");
        }
        employeeRepository.deleteById(id);
    }

    public Employee getCurrentEmployee() {
        User user = authenticatedUserService.getCurrentUser();
        return findById(user.getId());
    }

    private Long getBusinessId() {
        Employee employee = getCurrentEmployee();
        return employee.getBusinessId();
    }

}
