package com.GMH.digital.BarberPub.by.GMH.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GMH.digital.BarberPub.by.GMH.entities.User;

public interface UserRespository extends JpaRepository<User, Long> {

}
