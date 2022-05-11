package com.springsecurity.controller;

import com.springsecurity.entity.User;
import com.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public User getUserDetails(Principal user){
        Optional<User> optionalUser = userRepository.findByEmail(user.getName());
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

}
