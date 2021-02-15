package com.example.hbs.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "hotels")
public class Hotel {
    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private String hotelName;

    @Column(nullable = false)
    private String hotelLocation;

    @Column(nullable = false)
    private Integer noOfRooms;

    @Column(nullable = false)
    private Integer availableRooms;

    @Column(nullable = false)
    private Integer priceOfRoom;
}
