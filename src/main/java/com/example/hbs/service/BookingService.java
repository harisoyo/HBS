package com.example.hbs.service;

import com.example.hbs.dto.BookingRequestDto;
import com.example.hbs.dto.BookingResponseDto;
import com.example.hbs.exception.HbsException;
import com.example.hbs.model.Booking;
import com.example.hbs.model.Hotel;
import com.example.hbs.model.User;
import com.example.hbs.repository.BookingRepository;
import com.example.hbs.repository.HotelRepository;
import com.example.hbs.repository.UserRepository;
import com.example.hbs.service.mapper.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    BookingMapper bookingMapper;

    public BookingResponseDto viewBooking(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        Optional.ofNullable(booking).orElseThrow(() -> new HbsException("No such booking Id found"));
        User user = booking.getUser();
        if(!user.getId().equals(userId)){
            throw new HbsException("This booking is not owned by this User");
        }
        return bookingMapper.map(booking);
    }

    public BookingResponseDto addBooking(BookingRequestDto bookingRequestDto) {
        User user = userRepository.findById(bookingRequestDto.getUserId()).orElse(null);
        Optional.ofNullable(user).orElseThrow(() -> new HbsException("No such User present"));
        Hotel hotel = hotelRepository.findById(bookingRequestDto.getHotelId()).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HbsException("No such Hotel present"));
        Integer requiredRooms = bookingRequestDto.getNoOfRooms();
        if (requiredRooms <= 0) {
            throw new HbsException("Booking with Zero or Negative Required Rooms is not possible");
        }
        if (hotel.getAvailableRooms() < requiredRooms) {
            throw new HbsException("Number of Available Rooms are lesser than Required Rooms");
        }
        hotel.setAvailableRooms(hotel.getAvailableRooms() - requiredRooms);
        hotelRepository.save(hotel);
        Booking booking = Booking.builder()
                .user(user)
                .hotel(hotel)
                .checkIn(bookingRequestDto.getCheckIn())
                .checkOut(bookingRequestDto.getCheckOut())
                .bookingPrice(bookingRequestDto.getBookingPrice())
                .noOfRooms(bookingRequestDto.getNoOfRooms())
                .noOfPeople(bookingRequestDto.getNoOfPeople())
                .build();
        return bookingMapper.map(bookingRepository.save(booking));
    }

    public BookingResponseDto deleteBooking(Long id, Long userId) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        Optional.ofNullable(booking).orElseThrow(() -> new HbsException("No such booking Id found"));
        User user = booking.getUser();
        if (!user.getId().equals(userId)) {
            throw new HbsException("You are not allowed to delete this booking");
        }
        bookingRepository.deleteById(id);
        return bookingMapper.map(booking);
    }

}
