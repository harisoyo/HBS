package com.example.hbs.service;

import com.example.hbs.dto.BookingRequestDto;
import com.example.hbs.dto.BookingResponseDto;
import com.example.hbs.model.Booking;
import com.example.hbs.model.Hotel;
import com.example.hbs.repository.BookingRepository;
import com.example.hbs.repository.HotelRepository;
import com.example.hbs.repository.UserRepository;
import com.example.hbs.service.mapper.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public BookingResponseDto viewBooking(Long bookingId) {
        if (bookingRepository.findById(bookingId).isPresent()) {
            return bookingMapper.map(bookingRepository.findById(bookingId).get());
        } else {
            return null;
        }
    }

    public BookingResponseDto addBooking(BookingRequestDto bookingRequestDto) {
        if (userRepository.findById(bookingRequestDto.getUserId()).isPresent() &&
                hotelRepository.findById(bookingRequestDto.getHotelId()).isPresent()) {
            Hotel hotel = hotelRepository.findById(bookingRequestDto.getHotelId()).get();
            if (hotel.getAvailableRooms() >= bookingRequestDto.getNoOfRooms()) {
                hotel.setAvailableRooms(hotel.getAvailableRooms() - bookingRequestDto.getNoOfRooms());
                hotelRepository.save(hotel);
            } else {
                return null;
            }
            Booking booking = Booking.builder()
                    .user(userRepository.findById(bookingRequestDto.getUserId()).get())
                    .hotel(hotel)
                    .checkIn(bookingRequestDto.getCheckIn())
                    .checkOut(bookingRequestDto.getCheckOut())
                    .bookingPrice(bookingRequestDto.getBookingPrice())
                    .noOfRooms(bookingRequestDto.getNoOfRooms())
                    .noOfPeople(bookingRequestDto.getNoOfPeople())
                    .build();
            return bookingMapper.map(bookingRepository.save(booking));
        }
        else{
            return null;
        }
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
