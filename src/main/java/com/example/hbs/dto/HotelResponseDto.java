package com.example.hbs.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {
    private Long hotelId;

    private String hotelName;

    private String hotelLocation;

    private Integer availableRooms;

    private Integer priceOfRoom;
}
