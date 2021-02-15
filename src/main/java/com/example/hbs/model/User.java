package com.example.hbs.model;

import com.example.hbs.enums.Role;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user")
    private List<Hotel> hotels;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userContact;

    @Column(nullable = false)
    private Role userRole;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDate created_at;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate update_at;
}
