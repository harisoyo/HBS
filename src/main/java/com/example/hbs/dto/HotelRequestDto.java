package com.example.hbs.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDto {
    private Long userId;

    private String hotelName;

    private String hotelLocation;

    private Integer noOfRooms;

    private Integer availableRooms;

    private Integer priceOfRoom;
}
