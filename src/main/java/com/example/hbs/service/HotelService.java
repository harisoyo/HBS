package com.example.hbs.service;

import com.example.hbs.enums.Role;
import com.example.hbs.model.Hotel;
import com.example.hbs.model.User;
import com.example.hbs.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> findAllHotels(){
        return hotelRepository.findAll();
    }

    public void addHotel(Hotel hotel) {
        if(hotel.getUser().getUserRole().equals(Role.HOTEL_OWNER)){
            hotelRepository.save(hotel);
        }
    }

    public void deleteHotel(Long id) {
        if(hotelRepository.findById(id).isPresent()) {
            User user = hotelRepository.findById(id).get().getUser();
            if (user.getUserRole().equals(Role.HOTEL_OWNER)) {
                hotelRepository.deleteById(id);
            }
        }
    }

    public void updateHotel(Long id, Hotel hotel) {
        if(hotelRepository.findById(id).isPresent()) {
            User user = hotelRepository.findById(id).get().getUser();
            if (user.getUserRole().equals(Role.HOTEL_OWNER)) {
                hotelRepository.deleteById(id);
                hotelRepository.save(hotel);
            }
        }
    }
}
