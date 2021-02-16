package com.example.hbs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "Id", name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "Id", name = "hotel_id")
    private Hotel hotel;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false, updatable = false)
    private LocalDate checkIn;

    @Column(nullable = false, updatable = false)
    private LocalDate checkOut;

    @Column(nullable = false)
    private Integer noOfRooms;

    @Column(nullable = false)
    private Integer noOfPeople;

    @Column(nullable = false)
    private Integer bookingPrice;
}
