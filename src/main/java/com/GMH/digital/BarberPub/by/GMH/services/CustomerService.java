package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.BookingDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.CustomerDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.*;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.BookingRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.CustomerRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRespository schedulingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ServiceRespository serviceRepository;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public CustomerDTO getCurrentCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for current user"));
        return new CustomerDTO(customer);
    }

    public void updateCurrentCustomer(CustomerDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for current user"));

        customer.setName(dto.getName());
        customer.setBirthDate(dto.getBirthDate());
        customer.setPhone(dto.getPhone());
        customerRepository.save(customer);
    }

    @Transactional
    public Booking scheduleService(BookingDTO dto) {
        Employee barber = employeeRepository.findById(dto.getBarberId()).orElseThrow(() -> new ResourceNotFoundException("Id of Barber not Found"));

        Service service = serviceRepository.findById(dto.getServiceId()).orElseThrow(() -> new ResourceNotFoundException("Id of Service not Found"));

        Booking booking = new Booking();
        booking.setDate(dto.getDate());
        booking.setAppointmentHour(dto.getAppointmentHour());
        booking.setEmployee(barber);
        booking.setService(service);
        booking.setStatus(dto.getStatus());

        return schedulingRepository.save(booking);
    }


}
