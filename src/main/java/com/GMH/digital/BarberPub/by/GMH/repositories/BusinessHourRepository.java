package com.GMH.digital.BarberPub.by.GMH.repositories;

import com.GMH.digital.BarberPub.by.GMH.entities.BusinessHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessHourRepository extends JpaRepository<BusinessHour, Long> {

    List<BusinessHour> findByBusinessIdOrderByDayOfWeek(Long businessId);

    Optional<BusinessHour> findByBusinessIdAndDayOfWeek(Long businessId, DayOfWeek dayOfWeek);

    @Modifying
    @Query("DELETE FROM BusinessHour bh WHERE bh.business.id = :businessId")
    void deleteByBusinessId(@Param("businessId") Long businessId);
}
