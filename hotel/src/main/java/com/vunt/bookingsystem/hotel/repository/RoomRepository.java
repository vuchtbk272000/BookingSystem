package com.vunt.bookingsystem.hotel.repository;

import com.vunt.bookingsystem.hotel.models.Hotel;
import com.vunt.bookingsystem.hotel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "select * from room where hotel_id = ?1", nativeQuery = true)
    List<Room> roomListOfHotel(long hotelId);

}
