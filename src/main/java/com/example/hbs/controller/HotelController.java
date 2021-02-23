package com.example.hbs.controller;

import com.example.hbs.dto.HotelRequestDto;
import com.example.hbs.dto.HotelResponseDto;
import com.example.hbs.enums.SortBy;
import com.example.hbs.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping("/hotels/{pageNo}")
    public List<HotelResponseDto> showAllHotels(@PathVariable Integer pageNo, @RequestParam(defaultValue = "NONE") SortBy sortBy, @RequestParam(defaultValue = "10") Integer pageSize) {
        return hotelService.findAllHotels(pageNo, sortBy, pageSize);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hotels")
    public ResponseEntity<HotelResponseDto> insertHotel(@RequestBody HotelRequestDto hotelRequestDto) {
        HotelResponseDto hotelResponseDto = hotelService.addHotel(hotelRequestDto);
        if (hotelResponseDto != null)
            return new ResponseEntity<>(hotelResponseDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{id}")
    public ResponseEntity<HotelResponseDto> deleteHotel(@PathVariable Long id) {
        HotelResponseDto hotelResponseDto = hotelService.deleteHotel(id);
        if (hotelResponseDto != null)
            return new ResponseEntity<>(hotelResponseDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{id}")
    public ResponseEntity<HotelResponseDto> updateHotel(@RequestBody HotelResponseDto hotelResponseDto, @PathVariable Long id) {
        HotelResponseDto hotelResponseDto1 = hotelService.updateHotel(id, hotelResponseDto);
        if (hotelResponseDto1 != null)
            return new ResponseEntity<>(hotelResponseDto1, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


}

