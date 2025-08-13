package com.GMH.digital.BarberPub.by.GMH.controllers;

import com.GMH.digital.BarberPub.by.GMH.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class UserRestController {

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUser(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(user);
    }

}
