package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceCreateDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDto;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.entities.User;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRepository;
import org.springframework.security.access.AccessDeniedException;

import java.math.BigDecimal;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceManager {

    private final AuthenticatedUserService authenticatedUserService;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;

    public ServiceManager(
            AuthenticatedUserService authenticatedUserService,
            EmployeeRepository employeeRepository, ServiceRepository serviceRepository) {
        this.authenticatedUserService = authenticatedUserService;
        this.serviceRepository = serviceRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<ServiceDto> getServices() {
        Long businessId = getBusinessId();
        return serviceRepository.findByBusiness_Id(businessId)
                .stream()
                .map(ServiceDto::new)
                .toList();
    }

    public ServiceDto createService(ServiceCreateDTO dto) {
        User user = authenticatedUserService.getCurrentUser();
        Employee employee = employeeRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for current user"));

        Service service = new Service();
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPriceType(dto.getPriceType());
        service.setPrice(BigDecimal.valueOf(dto.getPrice()));
        service.setDurationMinutes(dto.getDurationMinutes());
        service.setBusiness(employee.getBusiness());
        service = serviceRepository.save(service);
        return new ServiceDto(service);
    }

    public void updateService(Long id, ServiceDto dto) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));
        if (service.getBusinessId() != getBusinessId()) {
            throw new AccessDeniedException("Service does not belong to the same business as the current user");
        }

        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPriceType(dto.getPriceType());
        service.setPrice(dto.getPrice());
        service.setDurationMinutes(dto.getDurationMinutes());

        serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + id));
        if (service.getBusinessId() != getBusinessId()) {
            throw new AccessDeniedException("Service does not belong to the same business as the current user");
        }

        serviceRepository.delete(service);
    }

    private Long getBusinessId() {
        User user = authenticatedUserService.getCurrentUser();
        Employee employee = employeeRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for current user"));
        return employee.getBusinessId();
    }
}
