package com.example.hbs.controller;

import com.example.hbs.dto.BookingRequestDto;
import com.example.hbs.dto.BookingResponseDto;
import com.example.hbs.dto.ResponseDto;
import com.example.hbs.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/booking")
    public ResponseDto<BookingResponseDto> addBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        BookingResponseDto bookingResponseDto = bookingService.addBooking(bookingRequestDto);
        if (bookingResponseDto != null)
            return new ResponseDto<>(bookingResponseDto);
        else
            return new ResponseDto<>(null);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/booking/{id}")
    public ResponseDto<BookingResponseDto> deleteBooking(@PathVariable Long id) {
        BookingResponseDto bookingResponseDto = bookingService.deleteBooking(id);
        if (bookingResponseDto != null)
            return new ResponseDto<>(bookingResponseDto);
        else
            return new ResponseDto<>(null);
    }
}
