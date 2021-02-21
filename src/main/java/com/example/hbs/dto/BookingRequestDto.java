package com.example.hbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {
    private Long userId;

    private Long hotelId;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private Integer noOfRooms;

    private Integer noOfPeople;

    private Integer bookingPrice;
}
