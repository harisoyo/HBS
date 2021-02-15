package com.example.hbs.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class Booking {
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Hotel hotel;
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false,updatable = false)
    private LocalDate checkIn;
    @Column(nullable = false,updatable = false)
    private LocalDate checkOut;
    @Column(nullable = false)
    private Integer noOfRooms;
    @Column(nullable = false)
    private Integer noOfPeople;
    @Column(nullable = false)
    private Integer bookingPrice;
}
