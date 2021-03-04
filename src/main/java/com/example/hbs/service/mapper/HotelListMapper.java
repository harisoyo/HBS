
package com.example.hbs.service.mapper;
import com.example.hbs.dto.HotelResponseDto;
import com.example.hbs.model.Hotel;
import com.example.hbs.service.IMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HotelListMapper implements IMapper<List<Hotel>, List<HotelResponseDto>> {
    @Override
    public List<HotelResponseDto> map(List<Hotel> hotels) {
        List<HotelResponseDto> hotelResponseDtos = new ArrayList<>();
        for (Hotel hotel : hotels) {
            HotelResponseDto responseDto = HotelResponseDto.builder().
                    Id(hotel.getId()).
                    hotelName(hotel.getHotelName()).
                    hotelLocation(hotel.getHotelLocation()).
                    availableRooms(hotel.getAvailableRooms()).
                    priceOfRoom(hotel.getPriceOfRoom()).build();
            hotelResponseDtos.add(responseDto);
        }
        return hotelResponseDtos;
    }

}
