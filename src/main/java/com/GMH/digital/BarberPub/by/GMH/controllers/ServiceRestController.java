package com.GMH.digital.BarberPub.by.GMH.controllers;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceCreateDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDto;
import com.GMH.digital.BarberPub.by.GMH.services.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceRestController {

    @Autowired
    private ServiceManager serviceManager;

    @GetMapping
    public List<ServiceDto> getServices() {
        return serviceManager.getServices();
    }

    @PostMapping
    public ResponseEntity<?> createService(@RequestBody ServiceCreateDTO dto) {
        ServiceDto service = serviceManager.createService(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> updateService(@PathVariable Long id, @RequestBody ServiceDto serviceDTO) {
        serviceManager.updateService(id, serviceDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceManager.deleteService(id);
        return ResponseEntity.noContent().build();
    }

}
