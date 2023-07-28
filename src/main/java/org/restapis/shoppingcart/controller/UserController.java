package org.restapis.shoppingcart.controller;


import org.modelmapper.ModelMapper;
import org.restapis.shoppingcart.dto.UserDto;
import org.restapis.shoppingcart.dto.UserProfileDto;
import org.restapis.shoppingcart.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path="/shoppingCart")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserServiceImpl userservice;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDtoResponse =  userservice.createUser(userDto);
        return new ResponseEntity<UserDto>(userDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/all/user")
    public List<UserDto> getAllPosts() {

        return userservice.getAllPosts().stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
    @PostMapping("/profile")
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfile, @RequestParam String username) throws IllegalAccessException {
        UserProfileDto userprofile =  userservice.createUserProfile(userProfile, username);
        return new ResponseEntity<>(userprofile, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userservice.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                              @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userservice.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userservice.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

}
