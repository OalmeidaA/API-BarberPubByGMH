package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.AddressDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessSocialsDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Address;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {

    private final BusinessRepository repository;
    private final EmployeeService employeeService;

    public BusinessService(BusinessRepository repository, EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    @Transactional(readOnly = true)
    public BusinessDTO getMyBusiness() {
        Long businessId = employeeService.getCurrentEmployee().getBusinessId();
        Business business = repository.findById(businessId)
                .orElseThrow(() -> new IllegalArgumentException("Business not found with id: " + businessId));
        return new BusinessDTO(business);
    }

    @Transactional
    public Business createBusiness(BusinessDTO dto) {
        Business newBarbershop = new Business(dto);
        return repository.save(newBarbershop);
    }

    @Transactional(readOnly = true)
    public List<BusinessDTO> findAllBusiness() {
        List<Business> businessList = repository.findAll();
        return businessList.stream()
                .map(BusinessDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public BusinessDTO updateBusinessAddress(AddressDTO addressDTO) {
        Long businessId = employeeService.getCurrentEmployee().getBusinessId();
        Business business = repository.findById(businessId)
                .orElseThrow(() -> new IllegalArgumentException("Business not found with id: " + businessId));

        Address address = business.getAddress();
        if (address == null) {
            address = new Address();
            business.setAddress(address);
        }

        // Atualizar os dados do endereço
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setComplement(addressDTO.getComplement());
        address.setNeighborhood(addressDTO.getNeighborhood());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());

        Business updatedBusiness = repository.save(business);
        return new BusinessDTO(updatedBusiness);
    }

    @Transactional
    public void updateSocialsLinks(BusinessSocialsDTO socialsDTO) {
        Business business = employeeService.getCurrentEmployee().getBusiness();

        business.setFacebookUrl(socialsDTO.getFacebookUrl());
        business.setInstagramUrl(socialsDTO.getInstagramUrl());
        business.setTwitterUrl(socialsDTO.getTwitterUrl());
        business.setYoutubeUrl(socialsDTO.getYoutubeUrl());
        business.setWebsiteUrl(socialsDTO.getWebsiteUrl());

        repository.save(business);
    }
}