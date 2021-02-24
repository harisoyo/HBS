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

    @RequestMapping(method = RequestMethod.GET, value = "/user/booking/{bookingId}")
    public ResponseDto<BookingResponseDto> viewBooking(@PathVariable Long bookingId, @RequestParam Long userId) {
        BookingResponseDto bookingResponseDto = bookingService.viewBooking(bookingId, userId);
        return new ResponseDto<>(bookingResponseDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/booking")
    public ResponseDto<BookingResponseDto> addBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        BookingResponseDto bookingResponseDto = bookingService.addBooking(bookingRequestDto);
        return new ResponseDto<>(bookingResponseDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/booking/{id}")
    public ResponseDto<BookingResponseDto> deleteBooking(@PathVariable Long id, @RequestParam Long userId) {
        BookingResponseDto bookingResponseDto = bookingService.deleteBooking(id, userId);
        return new ResponseDto<>(bookingResponseDto);
    }
}
