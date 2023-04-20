package com.vunt.bookingsystem.hotel.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer hotelId;

    private String roomNumber;

    private String description;

    private Integer status;

}
