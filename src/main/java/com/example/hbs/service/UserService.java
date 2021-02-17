package com.example.hbs.service;

import com.example.hbs.model.User;
import com.example.hbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public void updateUser(Long id, User user) {
        User temp = userRepository.findById(id).get();
        userRepository.delete(temp);
        userRepository.save(user);
    }
}
