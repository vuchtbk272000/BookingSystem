package com.vunt.bookingsystem.hotel.models.request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class RoomRequest {

    @Id
    private Long id;

    private Integer hotel_id;

    private String room_number;

    private String description;

    private Integer status;

}
