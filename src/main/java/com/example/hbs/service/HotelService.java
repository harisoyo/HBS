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
        String Id = id.toString();
        User user;
        if(hotelRepository.findById(Id).isPresent()) {
            user = hotelRepository.findById(Id).get().getUser();
            if (user.getUserRole().equals(Role.HOTEL_OWNER)) {
                hotelRepository.deleteById(Id);
            }
        }
    }

    public void updateHotel(Long id, Hotel hotel) {
        String Id = id.toString();
        User user;
        if(hotelRepository.findById(Id).isPresent()) {
            user = hotelRepository.findById(Id).get().getUser();
            if (user.getUserRole().equals(Role.HOTEL_OWNER)) {
                hotelRepository.deleteById(Id);
                hotelRepository.save(hotel);
            }
        }
    }
}
