package org.restapis.shoppingcart.controller;


import org.modelmapper.ModelMapper;
import org.restapis.shoppingcart.dto.AccountDto;
import org.restapis.shoppingcart.dto.UserDto;
import org.restapis.shoppingcart.dto.UserProfileDto;
import org.restapis.shoppingcart.model.UserProfile;
import org.restapis.shoppingcart.services.UserService;
import org.restapis.shoppingcart.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private UserService userservice;

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDtoResponse =  userservice.createUser(userDto);
        return new ResponseEntity<UserDto>(userDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<UserDto> getAllPosts() {

        return userservice.getAllPosts().stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userservice.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                              @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userservice.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userservice.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

    @PostMapping("/user/{id}/profile")
    public ResponseEntity<UserProfileDto> addUserProfile(@PathVariable(value = "id") long userId, @RequestBody UserProfileDto userProfileDto){
          UserProfileDto userProfileDto1 = userservice.addUserProfile(userId, userProfileDto);
        return new ResponseEntity<>(userProfileDto1, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}/profile/{profileId}")
    public ResponseEntity<UserProfileDto> updateUserProfile(@PathVariable(value = "id") long userId,
                     @PathVariable(value = "profileId") long profileId, @RequestBody UserProfileDto userProfileDto){
        UserProfileDto userProfileDto1 = userservice.updateUserProfile(userId, profileId, userProfileDto);
        return new ResponseEntity<>(userProfileDto1, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/profile/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "id") Long id) {
         userservice.findByUserIdAndId(userId, id);
        return new ResponseEntity<>("profile deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/profile")
    public Page<UserProfile> getAllUserProfileByUserId(@PathVariable(value = "userId") Long userId,
                                                       Pageable pageable) {
         return userservice.findByUserId(userId, pageable);
    }

    @PostMapping("/profile")
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfile, @RequestParam String username) throws IllegalAccessException {
        UserProfileDto userprofile =  userservice.createUserProfile(userProfile, username);
        return new ResponseEntity<>(userprofile, HttpStatus.CREATED);
    }

    @PostMapping("/account")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto, @RequestParam Long id) throws IllegalAccessException {
        AccountDto accountDtoResponse =  userservice.createAccount(accountDto, id);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);
    }

}
