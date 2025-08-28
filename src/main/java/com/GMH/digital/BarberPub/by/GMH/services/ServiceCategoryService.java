package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.ServiceCategoryCreateDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServiceCategoryDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.entities.Employee;
import com.GMH.digital.BarberPub.by.GMH.entities.ServiceCategory;
import com.GMH.digital.BarberPub.by.GMH.entities.User;
import com.GMH.digital.BarberPub.by.GMH.exception.ResourceNotFoundException;
import com.GMH.digital.BarberPub.by.GMH.repositories.EmployeeRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceCategoryRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCategoryService {
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final EmployeeRepository employeeRepository;

    public ServiceCategoryService(ServiceCategoryRepository serviceCategoryRepository,
                                  AuthenticatedUserService authenticatedUserService,
                                  EmployeeRepository employeeRepository) {
        this.serviceCategoryRepository = serviceCategoryRepository;
        this.authenticatedUserService = authenticatedUserService;
        this.employeeRepository = employeeRepository;
    }

    private Business getBusinessForCurrentUser() {
        User currentUser = authenticatedUserService.getCurrentUser();
        return employeeRepository.findById(currentUser.getId())
                .map(Employee::getBusiness)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for current user"));
    }

    public List<ServiceCategoryDTO> getServiceCategories() {
        Long businessId = getBusinessForCurrentUser().getId();
        List<ServiceCategory> categories = serviceCategoryRepository.findAllByBusiness_Id(businessId);
        return categories.stream()
                .map(ServiceCategoryDTO::new)
                .collect(Collectors.toList());
    }

    public ServiceCategoryDTO saveServiceCategory(ServiceCategoryCreateDTO createDTO) {
        Business business = getBusinessForCurrentUser();

        ServiceCategory category = new ServiceCategory();
        category.setName(createDTO.getName());
        category.setSortOrder(createDTO.getSortOrder());
        category.setBusiness(business);

        ServiceCategory savedCategory = serviceCategoryRepository.save(category);
        return new ServiceCategoryDTO(savedCategory);
    }

    public void updateServiceCategory(Long categoryId, ServiceCategoryDTO dto) {
        ServiceCategory category = serviceCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Service category not found with id: " + dto.getId()));

        assertOwnership(category, getBusinessForCurrentUser());

        category.setName(dto.getName());
        category.setSortOrder(dto.getSortOrder());
        serviceCategoryRepository.save(category);
    }

    public void deleteServiceCategory(Long categoryId) {
        ServiceCategory category = serviceCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Service category not found with id: " + categoryId));

        assertOwnership(category, getBusinessForCurrentUser());

        serviceCategoryRepository.delete(category);
    }

    private void assertOwnership(ServiceCategory category, Business currentBusiness) {
        if (!category.getBusiness().getId().equals(currentBusiness.getId())) {
            throw new AccessDeniedException("Service category does not belong to the same business as the current user");
        }
    }

}
