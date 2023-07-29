package org.restapis.shoppingcart.services;

import org.restapis.shoppingcart.dto.AccountDto;
import org.restapis.shoppingcart.dto.UserDto;
import org.restapis.shoppingcart.dto.UserProfileDto;
import org.restapis.shoppingcart.model.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserProfileDto createUserProfile(UserProfileDto userProfile, String username) throws IllegalAccessException;
    UserDto getUserById(Long userId);
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
    List<User> getAllPosts();

    AccountDto createAccount(AccountDto accountDto, Long id) throws IllegalAccessException;
}
