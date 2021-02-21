package com.example.hbs.controller;

import com.example.hbs.dto.BookingRequestDto;
import com.example.hbs.dto.BookingResponseDto;
import com.example.hbs.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/booking")
    public ResponseEntity<BookingResponseDto> addBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        System.out.println(bookingRequestDto);
        BookingResponseDto bookingResponseDto = bookingService.addBooking(bookingRequestDto);
        if (bookingResponseDto != null)
            return new ResponseEntity<>(bookingResponseDto,HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/booking/{id}")
    public ResponseEntity<BookingResponseDto> deleteBooking(@PathVariable Long id) {
        BookingResponseDto bookingResponseDto = bookingService.deleteBooking(id);
        if (bookingResponseDto != null)
            return new ResponseEntity<>(bookingResponseDto,HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
