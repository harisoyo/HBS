package com.example.hbs.service;

import com.example.hbs.DTO.AddUserDTO;
import com.example.hbs.DTO.UserDTO;
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

    public ResponseEntity<String> addUser(AddUserDTO addUserDto) {
        userRepository.save(addUserDto);
        return ResponseEntity.ok("User Added");
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public ResponseEntity<String> updateUser(Long id, UserDTO userDto) {
        if(userRepository.findById(id).isPresent()) {
            User temp = userRepository.findById(id).get();
            userRepository.delete(temp);
            userRepository.save(userDto);
            return ResponseEntity.ok("User updated");
        }
        else
        {
            return ResponseEntity.badRequest().body("No such id found");
        }
    }
}
