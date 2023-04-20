package com.vunt.bookingsystem.hotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;
import lombok.Getter;
@Data
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String address;

    private double rating;

    private String web;

    private String phone;

    private String logo;

    private String social;

//    public Integer getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public double getRating() {
//        return rating;
//    }
//
//    public void setRating(double rating) {
//        this.rating = rating;
//    }
//
//    public String getWeb() {
//        return web;
//    }
//
//    public void setWeb(String web) {
//        this.web = web;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getLogo() {
//        return logo;
//    }
//
//    public void setLogo(String logo) {
//        this.logo = logo;
//    }
//
//    public String getSocial() {
//        return social;
//    }
//
//    public void setSocial(String social) {
//        this.social = social;
//    }
//
//    public Hotel() {
//    }
//
//    public Hotel(String name, String email, String address, double rating, String web, String phone, String logo, String social) {
//        this.name = name;
//        this.email = email;
//        this.address = address;
//        this.rating = rating;
//        this.web = web;
//        this.phone = phone;
//        this.logo = logo;
//        this.social = social;
//    }
}
