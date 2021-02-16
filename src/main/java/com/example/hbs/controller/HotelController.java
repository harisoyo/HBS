package com.example.hbs.controller;

import com.example.hbs.model.Hotel;
import com.example.hbs.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping("/hotels")
    public Page<Hotel> showAllHotels() {
        return hotelService.findAllHotels();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hotels")
    public ResponseEntity<String> insertHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        return hotelService.deleteHotel(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{id}")
    public ResponseEntity<String> updateHotel(@RequestBody Hotel hotel, @PathVariable Long id) {
        return hotelService.updateHotel(id, hotel);
    }
}

