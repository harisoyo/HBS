package com.example.hbs.service;

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

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Page<Hotel> findAllHotels() {
        Pageable firstPage = PageRequest.of(0, 20);
        return hotelRepository.findAll(firstPage);
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

    public ResponseEntity<String> updateHotel(Long id, Hotel hotel) {
        if (hotelRepository.findById(id).isPresent()) {
            User user = hotelRepository.findById(id).get().getUser();
            if (user.getUserRole().equals(Role.HOTEL_OWNER)) {
                hotelRepository.deleteById(id);
                hotelRepository.save(hotel);
                return ResponseEntity.ok("Hotel Deleted");
            } else {
                return ResponseEntity.badRequest().body("Customers are not allowed to update a Hotel");
            }
        } else {
            return ResponseEntity.badRequest().body("No such Hotel is Present");
        }
    }
}
