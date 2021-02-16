package com.example.hbs.controller;

import com.example.hbs.model.Booking;
import com.example.hbs.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/booking")
    public ResponseEntity<String> addBooking(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/booking/{id}")
    public ResponseEntity<String> deleteBooking(@RequestParam Long id) {
        return bookingService.deleteBooking(id);
    }
}
