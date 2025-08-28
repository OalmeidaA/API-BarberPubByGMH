package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceCreateDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceDto;
import com.GMH.digital.BarberPub.by.GMH.dto.UpdateOrderDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.entities.ServiceCategory;
import com.GMH.digital.BarberPub.by.GMH.entities.User;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceCategoryRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceManager {

    private final AuthenticatedUserService authenticatedUserService;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;

    public ServiceManager(
            AuthenticatedUserService authenticatedUserService,
            EmployeeRepository employeeRepository, ServiceRepository serviceRepository, ServiceCategoryRepository serviceCategoryRepository) {
        this.authenticatedUserService = authenticatedUserService;
        this.serviceRepository = serviceRepository;
        this.employeeRepository = employeeRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
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

    @Transactional
    public void updateOrder(UpdateOrderDTO dto) {
        Long businessId = getBusinessId();

        // Updates categories order
        if (dto.getCategories() != null) {
            for (UpdateOrderDTO.CategoryOrderDTO categoryOrder : dto.getCategories()) {
                ServiceCategory category = serviceCategoryRepository.findById(categoryOrder.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryOrder.getId()));

                category.setSortOrder(categoryOrder.getSortOrder());
                serviceCategoryRepository.save(category);
            }
        }

        // Updates services order and category
        if (dto.getServices() != null) {
            for (UpdateOrderDTO.ServiceOrderDTO serviceOrder : dto.getServices()) {
                Service service = serviceRepository.findById(serviceOrder.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Service not found with id: " + serviceOrder.getId()));
                if (service.getBusinessId() != businessId) {
                    throw new AccessDeniedException("Service does not belong to the same business as the current user");
                }

                service.setSortOrder(serviceOrder.getSortOrder());

                if (serviceOrder.getCategoryId() != null) {
                    ServiceCategory category = serviceCategoryRepository.findById(serviceOrder.getCategoryId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("Category not found with id: " + serviceOrder.getCategoryId()));
                    service.setCategory(category);
                }

                serviceRepository.save(service);
            }
        }
    }

    private Long getBusinessId() {
        User user = authenticatedUserService.getCurrentUser();
        Employee employee = employeeRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for current user"));
        return employee.getBusinessId();
    }
}
