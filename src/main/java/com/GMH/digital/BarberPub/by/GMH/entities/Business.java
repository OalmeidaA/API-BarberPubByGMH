package com.GMH.digital.BarberPub.by.GMH.entities;

import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "business")
public class Business implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    @Column(unique = true)
    private String cnpj;

    private String address;

    @Lob
    private String description;

    public Business() {
    }

    public Business(Long id, String name, String cnpj, String address, String email, String phone, String description) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public Business(BusinessDTO dto) {
        id = dto.getId();
        name = dto.getName();
        email = dto.getEmail();
        phone = dto.getPhone();
        cnpj = dto.getCnpj();
        address = dto.getAddress();
        description = dto.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Business other = (Business) obj;
        return Objects.equals(cnpj, other.cnpj) && Objects.equals(id, other.id);
    }

}
