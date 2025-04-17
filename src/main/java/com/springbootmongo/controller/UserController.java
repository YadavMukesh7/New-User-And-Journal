package com.springbootmongo.controller;

import com.springbootmongo.api.response.WeatherResponse;
import com.springbootmongo.entity.User;
import com.springbootmongo.service.UserService;
import com.springbootmongo.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public ResponseEntity<?> getAllUsers() {
//        List<User> all = userService.findAll();
//        if (all != null && !all.isEmpty()) {
//            return new ResponseEntity<>(all, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            return new ResponseEntity<>(userService.updateUser(user, userName), HttpStatus.OK);
        } catch (Exception ex) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userService.deleteUser(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = " whether feels like " + weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hi " + userName + greeting, HttpStatus.OK);
    }

}
