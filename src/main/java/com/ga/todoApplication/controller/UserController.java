package com.ga.todoApplication.controller;

import com.ga.todoApplication.model.User;
import com.ga.todoApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth/users")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("calling createUser ==>");
        return userService.createUser(userObject);
    }
}



