package com.GMH.digital.BarberPub.by.GMH.controllers;

import com.GMH.digital.BarberPub.by.GMH.dto.BookingDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.CustomerDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Booking;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.services.CustomerService;
import com.GMH.digital.BarberPub.by.GMH.services.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@PreAuthorize("hasRole('USER')")
public class CustomerController {

    @Autowired
    private ServiceManager servicesBarberService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/me")
    public ResponseEntity<CustomerDTO> getCurrentCustomer() {
        CustomerDTO customerDTO = customerService.getCurrentCustomer();
        return ResponseEntity.ok(customerDTO);
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateCurrentCustomer(@RequestBody CustomerDTO dto) {
        customerService.updateCurrentCustomer(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> findAllServices() {
        List<Service> list = servicesBarberService.findAllServices();
        List<ServiceDTO> listdto = list.stream().map(x -> new ServiceDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok(listdto);
    }

    @PostMapping("/schedule")
    public ResponseEntity<BookingDTO> scheduleService(@RequestBody BookingDTO dto) {
        Booking newScheduling = customerService.scheduleService(dto);
        BookingDTO schedulingDto = new BookingDTO(newScheduling);
        return ResponseEntity.ok(schedulingDto);
    }


}
