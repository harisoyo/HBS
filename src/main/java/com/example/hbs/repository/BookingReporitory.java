package com.example.hbs.repository;

import com.example.hbs.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingReporitory extends JpaRepository<Booking, Long> {
}
