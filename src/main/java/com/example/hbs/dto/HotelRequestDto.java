package com.example.hbs.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDto {

    private Integer availableRooms;

    private Integer priceOfRoom;
}
