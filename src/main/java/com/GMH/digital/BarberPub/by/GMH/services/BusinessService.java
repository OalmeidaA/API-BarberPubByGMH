package com.GMH.digital.BarberPub.by.GMH.services;

import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {

    @Autowired
    private BusinessRespository repository;

    @Transactional
    public BusinessDTO createBusiness(BusinessDTO dto) {
        Business newBarbershop = new Business(dto);
        repository.save(newBarbershop);
        return new BusinessDTO(newBarbershop);
    }

    @Transactional(readOnly = true)
    public List<BusinessDTO> findAllBusiness() {
        List<Business> list = repository.findAll();
        List<BusinessDTO> listDto = list.stream().map(x -> new BusinessDTO(x)).collect(Collectors.toList());
        return listDto;
    }

}