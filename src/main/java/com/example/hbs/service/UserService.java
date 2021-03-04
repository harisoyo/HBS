package com.example.hbs.service;

import com.example.hbs.dto.UserRequestDto;
import com.example.hbs.dto.UserResponseDto;
import com.example.hbs.exception.HbsException;
import com.example.hbs.model.User;
import com.example.hbs.repository.UserRepository;
import com.example.hbs.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        if (userRequestDto.getUserName() == null || userRequestDto.getUserName().equals("")) {
            throw new HbsException("UserName is missing");
        }

        if (userRequestDto.getUserEmail() == null || userRequestDto.getUserEmail().equals("")) {
            throw new HbsException("User Email is missing");
        }
        if (userRequestDto.getUserContact() == null || userRequestDto.getUserContact().equals("")) {
            throw new HbsException("User contact is missing");
        }
        if (userRequestDto.getUserRole() == null ) {
            throw new HbsException("User role is missing");
        }

        User user = User.builder()
                .userName(userRequestDto.getUserName())
                .userEmail(userRequestDto.getUserEmail())
                .userContact(userRequestDto.getUserContact())
                .userRole(userRequestDto.getUserRole())
                .build();
        return userMapper.map(userRepository.save(user));
    }

    public List<UserResponseDto> findAllUser(Integer pageNo, Integer pageSize) {
        Pageable page = PageRequest.of(pageNo - 1, pageSize);
        Page<User> users = userRepository.findAll(page);
        List<User> user = users.getContent();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (User currentUser : user) {
            userResponseDtos.add(UserResponseDto.builder()
                    .Id(currentUser.getId())
                    .userName(currentUser.getUserName())
                    .userEmail(currentUser.getUserEmail())
                    .userContact(currentUser.getUserContact())
                    .userRole(currentUser.getUserRole())
                    .build());
        }
        return userResponseDtos;
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new HbsException("No such user id found"));
        if (userRequestDto.getUserName() != null) {
            user.setUserName(userRequestDto.getUserName());
        }
        if (userRequestDto.getUserEmail() != null) {
            user.setUserEmail(userRequestDto.getUserEmail());
        }
        if (userRequestDto.getUserContact() != null) {
            user.setUserContact(userRequestDto.getUserContact());
        }
        if (userRequestDto.getUserRole() != null) {
            user.setUserRole(userRequestDto.getUserRole());
        }
        return userMapper.map(userRepository.save(user));
    }
}
