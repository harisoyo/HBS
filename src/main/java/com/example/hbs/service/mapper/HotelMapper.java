package com.example.hbs.service.mapper;

import com.example.hbs.dto.HotelResponseDto;
import com.example.hbs.model.Hotel;
import com.example.hbs.service.IMapper;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper implements IMapper<Hotel, HotelResponseDto> {
    @Override
    public HotelResponseDto map(Hotel hotel) {
        return HotelResponseDto.builder()
                .Id(hotel.getId())
                .hotelName(hotel.getHotelName())
                .hotelLocation(hotel.getHotelLocation())
                .availableRooms(hotel.getAvailableRooms())
                .priceOfRoom(hotel.getPriceOfRoom())
                .build();
    }

}
