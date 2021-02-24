package com.example.hbs.controller;

import com.example.hbs.dto.ResponseDto;
import com.example.hbs.dto.UserRequestDto;
import com.example.hbs.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private com.example.hbs.service.UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseDto<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return new ResponseDto<>(userResponseDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public List<UserResponseDto> viewUser(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.findAllUser(pageNo, pageSize);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    public ResponseDto<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);
        if (userRequestDto != null)
            return new ResponseDto<>(userResponseDto);
        else
            return new ResponseDto<>(null);
    }
}