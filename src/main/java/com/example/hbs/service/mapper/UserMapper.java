package com.example.hbs.service.mapper;

import com.example.hbs.dto.UserResponseDto;
import com.example.hbs.model.User;
import com.example.hbs.service.IMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<User, UserResponseDto> {
    @Override
    public UserResponseDto map(User user){
        return UserResponseDto.builder()
                .Id(user.getId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userContact(user.getUserContact())
                .userRole(user.getUserRole())
                .build();
    }
}
