package com.GMH.digital.BarberPub.by.GMH.repositories;

import com.GMH.digital.BarberPub.by.GMH.entities.Customer;
import com.GMH.digital.BarberPub.by.GMH.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUser(User user);

}
