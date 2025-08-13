package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.RegisterDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Customer;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.User;
import com.GMH.digital.BarberPub.by.GMH.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Transactional
    public Map<String, Object> register(RegisterDTO dto) {
        if (dto.getFirebaseUid() == null || dto.getEmail() == null || dto.getRole() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "firebaseUid, email e role são obrigatórios");
        }

        Optional<User> existingUser = userRepository.findByFirebaseUid(dto.getFirebaseUid());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existe");
        }

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }

        User user = new User();
        user.setFirebaseUid(dto.getFirebaseUid());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole().toLowerCase());

        user = userRepository.save(user); // salva e retorna com ID

        if ("customer".equalsIgnoreCase(dto.getRole())) {
            Customer customer = new Customer();
//            customer.setId(user.getId());
            customer.setUser(user);
            customer.setName(dto.getName());
            customer.setEmail(dto.getEmail());
            customer.setPhone(dto.getPhone());
            customerService.save(customer);
        } else if ("employee".equalsIgnoreCase(dto.getRole())) {
            Employee employee = new Employee();
//            employee.setId(user.getId());
            employee.setUser(user);
            employee.setName(dto.getName());
            employee.setRole("barber"); // ou outro papel
            // employee.setBusinessId(dto.getBusinessId()); // descomente se necessário
            employeeService.save(employee);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role inválida");
        }

        return Map.of(
                "message", "Usuário registrado com sucesso",
                "userId", user.getId()
        );
    }
}
