package com.example.hbs.service;

import com.example.hbs.dto.BookingRequestDto;
import com.example.hbs.dto.BookingResponseDto;
import com.example.hbs.model.Booking;
import com.example.hbs.repository.BookingRepository;
import com.example.hbs.service.mapper.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    public BookingResponseDto addBooking(BookingRequestDto bookingRequestDto) {
        Booking booking = Booking.builder()
                .bookingPrice(bookingRequestDto.getBookingPrice())
                .noOfRooms(bookingRequestDto.getNoOfRooms())
                .noOfPeople(bookingRequestDto.getNoOfPeople())
                .build();
        return bookingMapper.map(bookingRepository.save(booking));
    }

    public BookingResponseDto deleteBooking(Long id) {
        if (bookingRepository.findById(id).isPresent()) {
            Booking booking = bookingRepository.findById(id).get();
            bookingRepository.deleteById(id);
            return bookingMapper.map(booking);
        } else {
            return null;
        }
    }
}
