package com.nicu.customer_service.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;

    private String streetName;

    private String streetNo;

    private String postalCode;

    private String country;


    //check if all fields of the address are completed (mandatory requirement)
    public boolean hasAllFieldsCompleted() {
        if (getCity() == null && getStreetName() == null
                && getStreetNo() == null
                && getPostalCode() == null
                && getCountry() == null) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNo='" + streetNo + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(getId(), address.getId()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreetName(), address.getStreetName()) && Objects.equals(getStreetNo(), address.getStreetNo()) && Objects.equals(getPostalCode(), address.getPostalCode()) && Objects.equals(getCountry(), address.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCity(), getStreetName(), getStreetNo(), getPostalCode(), getCountry());
    }
}








