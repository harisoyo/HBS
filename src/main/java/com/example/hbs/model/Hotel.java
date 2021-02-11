package com.example.hbs.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Hotel {
    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookings;
    @ManyToOne
    @JoinColumn
    private User user;
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String hotelId;
    @Column(nullable = false)
    private String hotelName;
    @Column(nullable = false)
    private String hotelLocation;
    @Column(nullable = false)
    private String typeOfRoom;
    @Column(nullable = false)
    private Integer noOfRooms;
    @Column(nullable = false)
    private Integer avaliableRooms;
    @Column(nullable = false)
    private Integer priceOfRoom;

}
