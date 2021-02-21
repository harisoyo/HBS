package com.example.hbs.service.mapper;

import com.example.hbs.dto.BookingResponseDto;
import com.example.hbs.model.Booking;
import com.example.hbs.service.IMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper implements IMapper<Booking, BookingResponseDto> {
    @Override
    public BookingResponseDto map(Booking booking) {
        return BookingResponseDto.builder()
                .bookingPrice(booking.getBookingPrice())
                .noOfRooms(booking.getNoOfRooms())
                .noOfPeople(booking.getNoOfPeople())
                .build();
    }
}
