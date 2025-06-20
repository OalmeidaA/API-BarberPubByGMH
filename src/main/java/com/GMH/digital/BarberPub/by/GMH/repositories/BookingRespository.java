package com.GMH.digital.BarberPub.by.GMH.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GMH.digital.BarberPub.by.GMH.entities.Booking;

public interface BookingRespository extends JpaRepository<Booking, Long> {

}
