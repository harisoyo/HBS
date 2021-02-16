package com.example.hbs.service;

import com.example.hbs.model.Booking;
import com.example.hbs.repository.BookingReporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    BookingReporitory bookingReporitory;

    public ResponseEntity<String> addBooking(Booking booking) {
        bookingReporitory.save(booking);
        return ResponseEntity.ok("Booking Added");
    }

    public ResponseEntity<String> deleteBooking(Long id) {
        if (bookingReporitory.findById(id).isPresent()) {
            bookingReporitory.deleteById(id);
            return ResponseEntity.ok("Booking Deleted");
        } else {
            return ResponseEntity.badRequest().body("No such Booking is present");
        }
    }
}
