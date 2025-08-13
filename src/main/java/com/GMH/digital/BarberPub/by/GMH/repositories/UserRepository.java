package com.GMH.digital.BarberPub.by.GMH.repositories;

import com.GMH.digital.BarberPub.by.GMH.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirebaseUid(String firebaseUid);

    Optional<User> findByEmail(String email);

}
