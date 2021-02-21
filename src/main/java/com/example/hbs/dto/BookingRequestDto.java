package com.example.hbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {
    private Integer noOfRooms;

    private Integer noOfPeople;

    private Integer bookingPrice;
}
