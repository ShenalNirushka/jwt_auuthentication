package com.nirushka.jwt.controller;

import com.nirushka.jwt.entity.User;
import com.nirushka.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    };

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/forAdmin"})
    public  String forAdmin(){
        return "this url is only for admin";
    }

    @GetMapping({"/forUser"})
    public  String forUser(){
        return "this url is only for user";
    }

}
