package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.UserRegisterDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.entities.Customer;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.User;
import com.GMH.digital.BarberPub.by.GMH.repositories.CustomerRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    final Logger log = LoggerFactory.getLogger(UserService.class);
    final private UserRepository userRepository;
    final private CustomerRepository customerRepository;
    final private EmployeeRepository employeeRepository;
    final private BusinessService businessService;

    public UserService(UserRepository userRepository,
                       CustomerRepository customerRepository,
                       EmployeeRepository employeeRepository,
                       BusinessService businessService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.businessService = businessService;
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void register(UserRegisterDTO dto) {
        if (dto.getFirebaseUid() == null || dto.getEmail() == null || dto.getRole() == null) {
            log.error("Email or firebase uid is missing");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "firebaseUid, email e role são obrigatórios");
        }

        User user = new User();

        Optional<User> optionalUser = userRepository.findByFirebaseUid(dto.getFirebaseUid());
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
            }

            user.setFirebaseUid(dto.getFirebaseUid());
            user.setEmail(dto.getEmail());
            user.setRole(dto.getRole().toLowerCase());
            user = userRepository.save(user);
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existe");
        }

        if (dto.getRole().equalsIgnoreCase("customer")) {
            Optional<Customer> existingCustomer = customerRepository.findById(user.getId());
            if (existingCustomer.isPresent()) {
                log.error("Já existe um cliente cadastrado com este usuário");
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um cliente cadastrado com este usuário");
            }

            Customer customer = new Customer();
            customer.setUser(user);
            customer.setName(dto.getName());
            customer.setEmail(dto.getEmail());
            customer.setPhoneNumber(dto.getPhoneNumber());
            customerRepository.save(customer);
        } else if ("employee".equalsIgnoreCase(dto.getRole())) {
            Optional<Employee> existingEmployee = employeeRepository.findById(user.getId());
            if (existingEmployee.isPresent()) {
                log.error("Já existe um funcionário cadastrado com este usuário");
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um funcionário cadastrado com este usuário");
            }

            BusinessDTO businessDto = new BusinessDTO();
            businessDto.setEmail(dto.getEmail());
            businessDto.setName(dto.getName() != null ? dto.getName() + "s's Business" : "New Business");
            businessDto.setPhone(dto.getPhoneNumber());
            Business business = businessService.createBusiness(businessDto);

            Employee employee = new Employee();
            employee.setUser(user);
            employee.setName(dto.getName() != null ? dto.getName() : "Your Name");
            employee.setEmail(dto.getEmail());
            employee.setCountryCode(dto.getCountryCode());
            employee.setPhoneNumber(dto.getPhoneNumber());

            employee.setRole("owner");
            employee.setBusiness(business);

            employeeRepository.save(employee);
        } else {
            log.error("Role inválida: {}", dto.getRole());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role inválida");
        }
    }
}
