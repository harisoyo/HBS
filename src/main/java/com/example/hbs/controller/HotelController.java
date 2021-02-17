package com.example.hbs.controller;

import com.example.hbs.dto.HotelResponseDto;
import com.example.hbs.model.Hotel;
import com.example.hbs.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping("/hotels/{pageNo}")
    public List<HotelResponseDto> showAllHotels(@PathVariable int pageNo) {
        return hotelService.findAllHotels(pageNo);
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
    public ResponseEntity<String> updateHotel(@RequestBody HotelResponseDto hotelResponseDto, @PathVariable Long id) {
        return hotelService.updateHotel(id, hotelResponseDto);
    }
}

