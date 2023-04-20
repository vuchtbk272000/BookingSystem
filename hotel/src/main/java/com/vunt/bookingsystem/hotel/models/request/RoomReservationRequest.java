package com.vunt.bookingsystem.hotel.models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomReservationRequest {
    @Id
    private Long id;
    private Long userId;
    private Long roomId;
    private int status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkinTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkoutTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private  LocalDateTime createdAt;

}
