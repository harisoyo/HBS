package com.example.hbs.controller;

import com.example.hbs.dto.UserRequestDto;
import com.example.hbs.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private com.example.hbs.service.UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{pageNo}}")
    public List<UserResponseDto> viewUser(@PathVariable Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.findAllUser(pageNo, pageSize);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);
        if (userRequestDto != null)
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}