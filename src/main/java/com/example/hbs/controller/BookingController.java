package com.example.hbs.controller;

import com.example.hbs.model.Booking;
import com.example.hbs.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/booking")
    public void addBooking(@RequestBody Booking booking){
        bookingService.addBooking(booking);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/user/booking/{id}")
    public void deleteBooking(@RequestParam Long id){
        bookingService.deleteBooking(id);
    }
}
