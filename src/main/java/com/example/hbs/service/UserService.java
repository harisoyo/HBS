package com.example.hbs.service;

import com.example.hbs.model.User;
import com.example.hbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> addUser(User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User Added");
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public ResponseEntity<String> updateUser(Long id, User user) {
        if(userRepository.findById(id).isPresent()) {
            User temp = userRepository.findById(id).get();
            userRepository.delete(temp);
            userRepository.save(user);
            return ResponseEntity.ok("User updated");
        }
        else
        {
            return ResponseEntity.badRequest().body("No such id found");
        }
    }
}
