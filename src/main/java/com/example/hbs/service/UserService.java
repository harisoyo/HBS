package com.example.hbs.service;

import com.example.hbs.dto.UserRequestDto;
import com.example.hbs.dto.UserResponseDto;
import com.example.hbs.model.User;
import com.example.hbs.repository.UserRepository;
import com.example.hbs.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = User.builder()
                .userName(userRequestDto.getUserName())
                .userEmail(userRequestDto.getUserEmail())
                .userContact(userRequestDto.getUserContact())
                .userRole(userRequestDto.getUserRole())
                .build();
        return userMapper.map(userRepository.save(user));
    }

    public List<UserResponseDto> findAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User currentUser : users) {
            userResponseDtos.add(UserResponseDto.builder()
                    .userName(currentUser.getUserName())
                    .userEmail(currentUser.getUserEmail())
                    .userContact(currentUser.getUserContact())
                    .userRole(currentUser.getUserRole())
                    .build());
        }
        return userResponseDtos;
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            if (userRequestDto.getUserName() != null)
                user.setUserName(userRequestDto.getUserName());
            if (userRequestDto.getUserEmail() != null)
                user.setUserEmail(userRequestDto.getUserEmail());
            if (userRequestDto.getUserContact() != null)
                user.setUserContact(userRequestDto.getUserContact());
            if (userRequestDto.getUserRole() != null)
                user.setUserRole(userRequestDto.getUserRole());
            return userMapper.map(userRepository.save(user));
        } else {
            return null;
        }
    }

}