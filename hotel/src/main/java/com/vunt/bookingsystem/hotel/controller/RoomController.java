package com.vunt.bookingsystem.hotel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vunt.bookingsystem.hotel.config.MessagingConfig;
import com.vunt.bookingsystem.hotel.models.Hotel;
import com.vunt.bookingsystem.hotel.models.Room;
import com.vunt.bookingsystem.hotel.models.RoomReservation;
import com.vunt.bookingsystem.hotel.models.request.RoomRequest;
import com.vunt.bookingsystem.hotel.models.request.RoomReservationRequest;
import com.vunt.bookingsystem.hotel.models.request.UserRequest;
import com.vunt.bookingsystem.hotel.repository.RoomRepository;
import com.vunt.bookingsystem.hotel.repository.RoomReservationRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(path="/hotel/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomReservationRepository roomReservationRepository;
    @Autowired
    private RabbitTemplate template;


    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewRoom (@RequestBody RoomRequest roomRequest) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Room room = new Room();
        room.setHotelId(roomRequest.getHotel_id());
        room.setRoomNumber(roomRequest.getRoom_number());
        room.setDescription(roomRequest.getDescription());
        roomRepository.save(room);
        return "Saved";
    }

    @PostMapping(path="/update/{roomId}") // Map ONLY POST Requests
    public @ResponseBody String updateUser (@PathVariable long roomId,@RequestBody RoomRequest roomRequest ) {
        Room room = roomRepository.findById(roomId).get();
        room.setStatus(room.getStatus());
        room.setRoomNumber(room.getRoomNumber());
        room.setDescription(room.getDescription());
        roomRepository.save(room);
        return "room information updated successfully";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Room> getAllRooms() {
        // This returns a JSON or XML with the users
        return roomRepository.findAll();
    }
    @GetMapping(path = "/reservations")
    public @ResponseBody Iterable<RoomReservation> getReservations() {
        // This returns a JSON or XML with the users
        return roomReservationRepository.findAll();
    }
    @GetMapping(path = "/{hotelId}/all")
    public @ResponseBody Iterable<Room> getAllRooms(@PathVariable Long hotelId) {
        return roomRepository.roomListOfHotel(hotelId);
    }
    @PostMapping(path = "/reserve")
    public  @ResponseBody String roomReservation(@RequestBody String request){

        System.out.println("received request ");
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, request);

        return "Successful Reservation";
    }

    @PutMapping(path = "approve/{roomReservationId}/{approve}")
    public  @ResponseBody String roomReservation(@PathVariable Long roomReservationId,@PathVariable int approve){
        RoomReservation roomReservation = roomReservationRepository.findById(roomReservationId).get();
        Room room = roomRepository.findById(roomReservation.getRoomId()).get();
        if (approve == 1){
            roomReservation.setStatus(1);
            room.setStatus(1);
            roomRepository.save(room);
            return "Reservation Approved";
        }
        else if (approve == 2){
            roomReservation.setStatus(2);
            roomRepository.save(room);
            return "Reservation rejected";
        }
        return "";
    }

    @PostMapping("/test/{roomId}")
    public String bookOrder(@PathVariable Long roomId) {

        //restaurantservice
        //payment service
        Optional<Room> room = roomRepository.findById(roomId);
        RoomReservation roomReservation = new RoomReservation();
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, roomReservation);
        return "Success !!";
    }
}