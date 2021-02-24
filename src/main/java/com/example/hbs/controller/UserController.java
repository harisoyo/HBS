package com.example.hbs.controller;

import com.example.hbs.DTO.AddUserDTO;
import com.example.hbs.DTO.UserDTO;
import com.example.hbs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private com.example.hbs.service.UserService userService;

    // request for registartion of new user.
    @RequestMapping(method = RequestMethod.POST,value="/user")
    public ResponseEntity<String> addUser(@RequestBody AddUserDTO addUserDto)
    {
        return userService.addUser(addUserDto);
    }

    @RequestMapping(method = RequestMethod.GET,value="/user")
    public List<User> viewUser()
    {
        return userService.findAllUser();
    }

    @RequestMapping(method = RequestMethod.PUT,value="/user/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDto, @PathVariable Long id)
            // replace User user with UserDTO userDto
    {
        return userService.updateUser(id,userDto);
    }
}
