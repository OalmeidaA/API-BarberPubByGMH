package com.GMH.digital.BarberPub.by.GMH.dto;

import com.GMH.digital.BarberPub.by.GMH.entities.Customer;
import com.GMH.digital.BarberPub.by.GMH.entities.Role;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;
    private LocalDate birthDate;

    public CustomerDTO() {

    }

    public CustomerDTO(String name, String email, String password, String phone, Role role, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.birthDate = birthDate;
    }

    public CustomerDTO(Customer user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        role = user.getRole();
        phone = user.getPhone();
        birthDate = user.getBirthDate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
