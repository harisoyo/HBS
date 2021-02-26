package com.example.hbs.service.mapper;

import com.example.hbs.dto.BookingResponseDto;
import com.example.hbs.model.Booking;
import com.example.hbs.service.IMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingListMapper implements IMapper<List<Booking>, List<BookingResponseDto>> {
    @Override
    public List<BookingResponseDto> map(List<Booking> bookings) {
        List<BookingResponseDto> bookingResponseDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingResponseDto responseDto = BookingResponseDto.builder()
                    .checkIn(booking.getCheckIn())
                    .checkOut(booking.getCheckOut())
                    .noOfRooms(booking.getNoOfRooms())
                    .bookingPrice(booking.getBookingPrice())
                    .noOfPeople(booking.getNoOfPeople())
                    .build();
            bookingResponseDtos.add(responseDto);
        }
        return bookingResponseDtos;
    }
}