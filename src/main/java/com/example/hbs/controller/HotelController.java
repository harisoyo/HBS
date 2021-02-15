package com.example.hbs.controller;

import java.util.List;
import com.example.hbs.model.Hotel;
import com.example.hbs.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping("/hotels")
    public List<Hotel> showAllHotels(){
        return hotelService.findAllHotels();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/hotels")
    public void insertHotel(@RequestBody Hotel hotel){
        hotelService.addHotel(hotel);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/hotels/{id}")
    public void deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/hotels/{id}")
    public void updateHotel(@RequestBody Hotel hotel, @PathVariable Long id){
        hotelService.updateHotel(id, hotel);
    }
}

