package com.example.hbs.controller;

import com.example.hbs.dto.HotelRequestDto;
import com.example.hbs.dto.HotelResponseDto;
import com.example.hbs.dto.ResponseDto;
import com.example.hbs.enums.SortBy;
import com.example.hbs.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping("/hotels")
    public List<HotelResponseDto> showAllHotels(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "NONE") SortBy sortBy, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = false) Long userId) {
        return hotelService.findAllHotels(pageNo, sortBy, pageSize, userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hotels")
    public ResponseDto<HotelResponseDto> insertHotel(@RequestBody HotelRequestDto hotelRequestDto) {
        HotelResponseDto hotelResponseDto = hotelService.addHotel(hotelRequestDto);
        return new ResponseDto<>(hotelResponseDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{id}")
    public ResponseDto<HotelResponseDto> updateHotel(@RequestBody HotelRequestDto hotelRequestDto, @PathVariable Long id, @RequestParam(required = false) Long userId) {
        HotelResponseDto hotelResponseDto1 = hotelService.updateHotel(id, hotelRequestDto, userId);
        return new ResponseDto<>(hotelResponseDto1);
    }


}

