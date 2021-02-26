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
import com.example.hbs.service.mapper.BookingListMapper;
import com.example.hbs.service.mapper.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    BookingListMapper bookingListMapper;

    private void CheckIfUserIdIsCorrect(Long userId){
        if(userId == null){
            throw new HbsException("Missing userId Params");
        }
        if(userId <= 0){
            throw new HbsException("Zero or negative userId is not allowed");
        }
    }

    public BookingResponseDto viewBooking(Long bookingId, Long userId) {
        CheckIfUserIdIsCorrect(userId);
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        Optional.ofNullable(booking).orElseThrow(() -> new HbsException("No such booking Id found"));
        User user = booking.getUser();
        if (!user.getId().equals(userId)) {
            throw new HbsException("This booking is not owned by this User");
        }
        return bookingMapper.map(booking);
    }


    public List<BookingResponseDto> findAllBookingsOfHotel(Long hotelId, Integer pageNo, Integer pageSize) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HbsException("No hotel found with this Hotel_id"));
        Pageable page = PageRequest.of(pageNo - 1, pageSize);
        Page<Booking> bookings = bookingRepository.findAllBookingofHotel(hotelId, page);
        List<Booking> booking = bookings.getContent();
        return bookingListMapper.map(booking);
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
        CheckIfUserIdIsCorrect(userId);
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
