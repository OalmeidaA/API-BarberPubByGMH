package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.CustomerDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Customer;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.User;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.CustomerRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRepository;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public CustomerService(CustomerRepository customerRepository,
                           EmployeeRepository employeeRepository,
                           AuthenticatedUserService authenticatedUserService) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getCurrentCustomer() {
        User user = authenticatedUserService.getCurrentUser();
        return customerRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for current user"));
    }

    public void updateCurrentCustomer(CustomerDTO dto) {
        Customer customer = getCurrentCustomer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setCountryCode(dto.getCountryCode());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setBirthDate(dto.getBirthDate());
        customerRepository.save(customer);
    }


    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public List<CustomerDTO> getAllCustomers() {
        Customer currentCustomer = getCurrentCustomer();
        return customerRepository.findByBusiness_Id(currentCustomer.getBusinessId())
                .stream()
                .map(CustomerDTO::new)
                .toList();
    }


    private Employee getCurrentEmployee() {
        User user = authenticatedUserService.getCurrentUser();
        return employeeRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for current user"));
    }

    public void createCustomer(CustomerDTO dto) {
        Employee employee = getCurrentEmployee();

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setImage(dto.getImage());
        customer.setCountryCode(dto.getCountryCode());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setBirthDate(dto.getBirthDate());
        customer.setInternalNote(dto.getInternalNote());
        customer.setEmail(dto.getEmail());

        customer.setBusiness(employee.getBusiness());

        customerRepository.save(customer);
    }

    public void updateCustomer(long id, CustomerDTO dto) {
        Customer customer = findById(id);
        Employee currentEmployee = getCurrentEmployee();

        if (!Objects.equals(customer.getBusinessId(), currentEmployee.getBusinessId())) {
            throw new AccessDeniedException("Customer does not belong to the same business as the current user");
        }

        customer.setName(dto.getName());
        customer.setImage(customer.getImage());
        customer.setCountryCode(dto.getCountryCode());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setBirthDate(dto.getBirthDate());
        customer.setInternalNote(dto.getInternalNote());
        customer.setEmail(dto.getEmail());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = findById(id);
        Employee currentEmployee = getCurrentEmployee();

        if (!Objects.equals(customer.getBusinessId(), currentEmployee.getBusinessId())) {
            throw new AccessDeniedException("Customer does not belong to the same business as the current user");
        }

        customerRepository.delete(customer);
    }
}
