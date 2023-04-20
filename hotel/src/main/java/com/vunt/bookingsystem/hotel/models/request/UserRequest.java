package com.vunt.bookingsystem.hotel.models.request;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String username;
    private String password;
    private int role;
    private int gender;
    private String phone;
    private String email;
    private String address;



}
