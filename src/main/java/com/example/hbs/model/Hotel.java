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
    @JoinColumn(nullable = false, referencedColumnName = "Id", name = "user_id")
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
