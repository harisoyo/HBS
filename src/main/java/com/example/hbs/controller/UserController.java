package com.example.hbs.controller;

import com.example.hbs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private com.example.hbs.service.UserService userService;

    // request for registartion of new user.
    @RequestMapping(method = RequestMethod.POST,value="/user")
    public void addUser(@RequestBody User user)
    {
        userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.GET,value="/user")
    public List<User> viewUser()
    {
        return userService.findAllUser();
    }

    @RequestMapping(method = RequestMethod.PUT,value="/user/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id)
    {
        userService.updateUser(id,user);
    }
}
