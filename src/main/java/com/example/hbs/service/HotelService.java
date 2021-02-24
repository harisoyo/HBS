package com.example.hbs.service;

import com.example.hbs.dto.HotelRequestDto;
import com.example.hbs.dto.HotelResponseDto;
import com.example.hbs.enums.Role;
import com.example.hbs.enums.SortBy;
import com.example.hbs.exception.HbsException;
import com.example.hbs.model.Hotel;
import com.example.hbs.model.User;
import com.example.hbs.repository.HotelRepository;
import com.example.hbs.repository.UserRepository;
import com.example.hbs.service.mapper.HotelListMapper;
import com.example.hbs.service.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private HotelListMapper hotelListMapper;

    public List<HotelResponseDto> findAllHotels(Integer pageNo, SortBy sortBy, Integer pageSize) {
        String sort = "Id";
        if (sortBy.equals(SortBy.PRICE))
            sort = "priceOfRoom";
        if (sortBy.equals(SortBy.ROOM))
            sort = "noOfRooms";
        Pageable page;
        if (sort.equals("Id"))
            page = PageRequest.of(pageNo - 1, pageSize);
        else
            page = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort));
        Page<Hotel> hotels = hotelRepository.findAll(page);
        List<Hotel> hotel = hotels.getContent();
        return hotelListMapper.map(hotel);
    }

    public HotelResponseDto addHotel(HotelRequestDto hotelRequestDto) {
        User user = userRepository.findById(hotelRequestDto.getUserId()).orElse(null);
        Optional.ofNullable(user).orElseThrow(() -> new HbsException("No user found"));
        if (user.getUserRole().equals(Role.CUSTOMER)) {
            throw new HbsException("Customers are not allowed to add Hotels");
        }
        Hotel hotel = Hotel.builder().user(user).
                hotelName(hotelRequestDto.getHotelName()).
                hotelLocation(hotelRequestDto.getHotelLocation()).
                availableRooms(hotelRequestDto.getAvailableRooms()).
                noOfRooms(hotelRequestDto.getNoOfRooms()).
                priceOfRoom(hotelRequestDto.getPriceOfRoom()).
                build();
        return hotelMapper.map(hotelRepository.save(hotel));
    }

    public HotelResponseDto deleteHotel(Long id, Long userId) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HbsException("Hotel not found"));
        User user = hotel.getUser();
        if (!user.getId().equals(userId)) {
            throw new HbsException("You are not the owner of this hotel");
        }
        hotelRepository.deleteById(id);
        return hotelMapper.map(hotel);

    }

    public HotelResponseDto updateHotel(Long id, HotelResponseDto hotelResponseDto, Long userId) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HbsException("Hotel not found"));
        User user = hotel.getUser();
        if (!user.getId().equals(userId)) {
            throw new HbsException("You are not the owner of this hotel");
        }
        if (hotelResponseDto.getAvailableRooms() != null)
            hotel.setAvailableRooms(hotelResponseDto.getAvailableRooms());
        if (hotelResponseDto.getPriceOfRoom() != null)
            hotel.setPriceOfRoom(hotelResponseDto.getPriceOfRoom());
        return hotelMapper.map(hotelRepository.save(hotel));
    }

}
