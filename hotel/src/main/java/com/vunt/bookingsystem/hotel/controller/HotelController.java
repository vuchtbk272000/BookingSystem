package com.vunt.bookingsystem.hotel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vunt.bookingsystem.hotel.models.Hotel;
import com.vunt.bookingsystem.hotel.models.request.HotelRequest;
import com.vunt.bookingsystem.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;

@Controller
@RequestMapping(path="/hotel")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name,
                                            @RequestParam String email,
                                            @RequestParam String address,
                                            @RequestParam Double rating,
                                            @RequestParam String web,
                                            @RequestParam String phone,
                                            @RequestParam String logo,
                                            @RequestParam String social) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Hotel n = new Hotel();
        n.setName(name);
        n.setEmail(email);
        n.setAddress(address);
        n.setRating(rating);
        n.setWeb(web);
        n.setPhone(phone);
        n.setLogo(logo);
        n.setSocial(social);
        hotelRepository.save(n);
        return "Saved";
    }

    @PutMapping(path="/update",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    public @ResponseBody Hotel updateUser (@RequestBody HotelRequest hotelRequest) {
//        return 1;
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Hotel hotel = hotelRepository.findById(hotelRequest.getId()).get();
        hotel.setName(hotelRequest.getName());
        hotel.setEmail(hotelRequest.getEmail());
        hotel.setAddress(hotelRequest.getAddress());
        hotel.setRating(hotelRequest.getRating());
        hotel.setWeb(hotelRequest.getWeb());
        hotel.setPhone(hotelRequest.getPhone());
        hotel.setLogo(hotelRequest.getLogo());
        hotel.setSocial(hotelRequest.getSocial());
        hotelRepository.save(hotel);
        return hotel;
    }


    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Hotel> getAllUsers() {
        // This returns a JSON or XML with the users
        return hotelRepository.findAll();
    }


    @GetMapping(path="/test")
    public @ResponseBody Hotel test(@RequestBody  String str) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(str);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Hotel hotel = mapper.convertValue(node.get("hotel"), Hotel.class);
        // This returns a JSON or XML with the users
        return hotel;
    }
}
