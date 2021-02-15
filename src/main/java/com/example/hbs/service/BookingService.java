package com.example.hbs.service;

import com.example.hbs.model.Booking;
import com.example.hbs.repository.BookingReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    BookingReporitory bookingReporitory;

    public void addBooking(Booking booking) {
        bookingReporitory.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingReporitory.deleteById(id);
    }
}
