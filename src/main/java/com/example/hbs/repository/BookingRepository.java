package com.example.hbs.repository;

import com.example.hbs.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select b from Booking b where b.hotel.Id = :Id")
    Page<Booking> findAllBookingofHotel(Long Id, Pageable page);
}
