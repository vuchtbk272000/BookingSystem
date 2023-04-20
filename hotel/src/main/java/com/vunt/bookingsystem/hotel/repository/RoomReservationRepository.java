package com.vunt.bookingsystem.hotel.repository;

import com.vunt.bookingsystem.hotel.models.Room;
import com.vunt.bookingsystem.hotel.models.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

}
