package com.vunt.bookingsystem.hotel.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vunt.bookingsystem.hotel.config.MessagingConfig;
import com.vunt.bookingsystem.hotel.models.RoomReservation;
import com.vunt.bookingsystem.hotel.models.request.RoomReservationRequest;
import com.vunt.bookingsystem.hotel.repository.RoomReservationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class RoomReservationMessage {

    @Autowired
    private RoomReservationRepository roomReservationRepository;
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(String request) {

        System.out.println("Message received from queue : " + request);
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        JsonNode node = null;
        try {
            node = mapper.readTree(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        RoomReservationRequest roomReservationRequest = mapper.convertValue(node.get("roomReservationRequest"), RoomReservationRequest.class);

        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setUserId(roomReservationRequest.getUserId());
        roomReservation.setRoomId(roomReservationRequest.getRoomId());
        LocalDateTime localDateTime = LocalDateTime.now();
        roomReservation.setCreatedAt(localDateTime);
        roomReservation.setCheckinTime(roomReservationRequest.getCheckinTime());
        roomReservation.setCheckoutTime(roomReservationRequest.getCheckoutTime());
        roomReservationRepository.save(roomReservation);

    }
}
