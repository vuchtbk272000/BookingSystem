package com.vunt.bookingsystem.hotel.models.request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
//import lombok.NoArgsConstructor;


//import lombok.Setter;
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
////@Setter
@Data
@Entity
public class HotelRequest {

    @Id
    private Long id;

    private String name;

    private String email;

    private String address;

    private double rating;

    private String web;

    private String phone;

    private String logo;

    private String social;

}
