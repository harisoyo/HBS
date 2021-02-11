package com.example.hbs.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="my_user_table")
public class User {

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
    @OneToMany(mappedBy = "user")
    private List<Hotel> hotels;
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String userContact;
    public enum Role{
        CUSTOMER,
        HOTELOWNER
    }
    @Column(nullable = false)
    private Role userRole;
    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Date created_at;
    @Column(nullable = false)
    @CreationTimestamp
    private Date update_at;

}
