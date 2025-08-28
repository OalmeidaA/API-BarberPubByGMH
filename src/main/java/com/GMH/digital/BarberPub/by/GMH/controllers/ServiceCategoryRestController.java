package com.GMH.digital.BarberPub.by.GMH.controllers;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceCategoryCreateDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceCategoryDTO;
import com.GMH.digital.BarberPub.by.GMH.services.ServiceCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service-categories")
public class ServiceCategoryRestController {
    private final ServiceCategoryService serviceCategoryService;

    public ServiceCategoryRestController(ServiceCategoryService serviceCategoryService) {
        this.serviceCategoryService = serviceCategoryService;
    }

    @GetMapping
    public List<ServiceCategoryDTO> getServiceCategories() {
        return serviceCategoryService.getServiceCategories();
    }

    @PostMapping
    public ResponseEntity<ServiceCategoryDTO> saveServiceCategory(@RequestBody ServiceCategoryCreateDTO createDTO) {
        ServiceCategoryDTO savedCategory = serviceCategoryService.saveServiceCategory(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<Void> deleteServiceCategory(@PathVariable Long categoryId) {
        serviceCategoryService.deleteServiceCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<Void> updateServiceCategory(@PathVariable Long categoryId, @RequestBody ServiceCategoryDTO dto) {
        serviceCategoryService.updateServiceCategory(categoryId, dto);
        return ResponseEntity.noContent().build();
    }
}
