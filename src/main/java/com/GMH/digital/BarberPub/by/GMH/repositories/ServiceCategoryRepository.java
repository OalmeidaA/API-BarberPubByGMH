package com.GMH.digital.BarberPub.by.GMH.repositories;

import com.GMH.digital.BarberPub.by.GMH.entities.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {

    List<ServiceCategory> findAllByBusiness_Id(long id);

}
