package com.example.hbs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
public class Hotel {
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "Id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "id")
    private Long Id;

    @Column(nullable = false, name = "hotel_name")
    private String hotelName;

    @Column(nullable = false, name = "hotel_location")
    private String hotelLocation;

    @Column(nullable = false, name = "no_of_rooms")
    private Integer noOfRooms;

    @Column(nullable = false, name = "available_rooms")
    private Integer availableRooms;

    @Column(nullable = false, name = "price_of_room")
    private Integer priceOfRoom;
}
