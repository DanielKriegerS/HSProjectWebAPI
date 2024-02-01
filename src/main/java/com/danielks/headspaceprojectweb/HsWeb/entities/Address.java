package com.danielks.headspaceprojectweb.HsWeb.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="tb_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "user_id")
    private UUID userId;
    private int number;
    private String district;
    private String street;
    private String cep;

    public Address() {
    }

    public Address(UUID uuid, UUID userId, int number, String district, String street, String cep) {
        this.uuid = uuid;
        this.userId = userId;
        this.number = number;
        this.district = district;
        this.street = street;
        this.cep = cep;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return uuid.equals(address.uuid) && userId.equals(address.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, userId);
    }
}
