package com.example.hbs.service;

import com.example.hbs.dto.HotelResponseDto;
import com.example.hbs.enums.Role;
import com.example.hbs.model.Hotel;
import com.example.hbs.model.User;
import com.example.hbs.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<HotelResponseDto> findAllHotels(int pageNo) {
        Pageable firstPage = PageRequest.of(pageNo-1, 20);
        Page<Hotel> hotels = hotelRepository.findAll(firstPage);
        List<HotelResponseDto> hotelResponseDtos = new ArrayList<>();
        for(int iterateHotel = 0;iterateHotel<20;iterateHotel++){
            Hotel hotel = hotels.getContent().get(iterateHotel);
            HotelResponseDto responseDto = HotelResponseDto.builder().
                    hotelName(hotel.getHotelName()).
                    hotelLocation(hotel.getHotelLocation()).
                    availableRooms(hotel.getAvailableRooms()).
                    priceOfRoom(hotel.getPriceOfRoom()).build();
            hotelResponseDtos.add(responseDto);
        }
        return hotelResponseDtos;
    }

    public ResponseEntity<String> addHotel(Hotel hotel) {
        if (hotel.getUser().getUserRole().equals(Role.HOTEL_OWNER)) {
            hotelRepository.save(hotel);
            return ResponseEntity.ok("Hotel Added");
        } else {
            return ResponseEntity.badRequest().body("Customers are not allowed to add a Hotel");
        }
    }

    public ResponseEntity<String> deleteHotel(Long id) {
        if (hotelRepository.findById(id).isPresent()) {
            User user = hotelRepository.findById(id).get().getUser();
            if (user.getUserRole().equals(Role.HOTEL_OWNER)) {
                hotelRepository.deleteById(id);
                return ResponseEntity.ok("Hotel Deleted");
            } else {
                return ResponseEntity.badRequest().body("Customers are not allowed to delete a Hotel");
            }
        } else {
            return ResponseEntity.badRequest().body("No such Hotel is Present");
        }
    }

    public ResponseEntity<String> updateHotel(Long id, HotelResponseDto hotelResponseDto) {
        if (hotelRepository.findById(id).isPresent()) {
            User user = hotelRepository.findById(id).get().getUser();
            if (user.getUserRole().equals(Role.HOTEL_OWNER)) {
                Hotel hotel = hotelRepository.getOne(id);
                hotel.setAvailableRooms(hotelResponseDto.getAvailableRooms());
                hotel.setPriceOfRoom(hotelResponseDto.getPriceOfRoom());
                hotelRepository.save(hotel);
                return ResponseEntity.ok("Hotel Updated");
            } else {
                return ResponseEntity.badRequest().body("Customers are not allowed to update a Hotel");
            }
        } else {
            return ResponseEntity.badRequest().body("No such Hotel is Present");
        }
    }
}
