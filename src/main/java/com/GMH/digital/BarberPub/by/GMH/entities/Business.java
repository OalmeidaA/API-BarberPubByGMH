package com.GMH.digital.BarberPub.by.GMH.entities;

import com.GMH.digital.BarberPub.by.GMH.dto.BusinessDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @Lob
    private String description;

    private String facebookUrl;
    private String instagramUrl;
    private String twitterUrl;
    private String youtubeUrl;
    private String websiteUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    private BusinessCategory category;

    @ElementCollection(targetClass = Amenity.class)
    @CollectionTable(name = "business_amenities", joinColumns = @JoinColumn(name = "business_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private Set<Amenity> amenities = new HashSet<>();

    public Business() {
    }

    public Business(Long id, String name, String cnpj, Address address, String email, String phone, String description) {
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
        description = dto.getDescription();

        if (dto.getAddress() != null) {
            this.address = new Address(
                dto.getAddress().getStreet(),
                dto.getAddress().getNumber(),
                dto.getAddress().getComplement(),
                dto.getAddress().getNeighborhood(),
                dto.getAddress().getCity(),
                dto.getAddress().getState(),
                dto.getAddress().getPostalCode(),
                dto.getAddress().getCountry()
            );
        }
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenity> amenities) {
        this.amenities = amenities;
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

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public BusinessCategory getCategory() {
        return category;
    }

    public void setCategory(BusinessCategory category) {
        this.category = category;
    }

}
