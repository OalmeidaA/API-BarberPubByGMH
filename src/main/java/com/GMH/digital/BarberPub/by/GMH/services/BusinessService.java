package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.AddressDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessAmenitiesDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessBasicDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessSocialsDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessHourDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BusinessHoursDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Address;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.entities.BusinessHour;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessHourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {

    private final BusinessRepository repository;
    private final BusinessHourRepository businessHourRepository;
    private final EmployeeService employeeService;

    public BusinessService(BusinessRepository repository, BusinessHourRepository businessHourRepository, EmployeeService employeeService) {
        this.repository = repository;
        this.businessHourRepository = businessHourRepository;
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

    @Transactional
    public void updateAmenities(BusinessAmenitiesDTO amenitiesDTO) {
        Business business = employeeService.getCurrentEmployee().getBusiness();

        business.setAmenities(amenitiesDTO.getAmenities());

        repository.save(business);
    }

    @Transactional
    public void updateBasicInfo(BusinessBasicDTO basicDTO) {
        Business business = employeeService.getCurrentEmployee().getBusiness();

        business.setName(basicDTO.getName());
        business.setDescription(basicDTO.getDescription());
        business.setPhone(basicDTO.getPhone());
        business.setEmail(basicDTO.getEmail());
        business.setCnpj(basicDTO.getCnpj());
        business.setCategory(basicDTO.getCategory());

        repository.save(business);
    }

    @Transactional
    public void updateBusinessHours(BusinessHoursDTO businessHoursDTO) {
        Business business = employeeService.getCurrentEmployee().getBusiness();

        businessHourRepository.deleteByBusinessId(business.getId());

        List<BusinessHour> newBusinessHours = businessHoursDTO.getBusinessHours().stream()
                .map(dto -> {
                    BusinessHour businessHour = new BusinessHour();
                    businessHour.setDayOfWeek(dto.getDayOfWeek());
                    businessHour.setClosed(dto.getClosed());
                    businessHour.setOpeningTime(dto.getOpeningTime());
                    businessHour.setClosingTime(dto.getClosingTime());
                    businessHour.setBusiness(business);
                    return businessHour;
                })
                .collect(Collectors.toList());

        businessHourRepository.saveAll(newBusinessHours);
    }

    @Transactional(readOnly = true)
    public BusinessHoursDTO getBusinessHours() {
        Long businessId = employeeService.getCurrentEmployee().getBusinessId();
        List<BusinessHour> businessHours = businessHourRepository.findByBusinessIdOrderByDayOfWeek(businessId);

        List<BusinessHourDTO> businessHourDTOs = businessHours.stream()
                .map(bh -> new BusinessHourDTO(
                    bh.getId(),
                    bh.getDayOfWeek(),
                    bh.getClosed(),
                    bh.getOpeningTime(),
                    bh.getClosingTime()
                ))
                .collect(Collectors.toList());

        return new BusinessHoursDTO(businessHourDTOs);
    }

}
