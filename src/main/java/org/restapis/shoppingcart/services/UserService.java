package org.restapis.shoppingcart.services;

import org.restapis.shoppingcart.dto.AccountDto;
import org.restapis.shoppingcart.dto.UserDto;
import org.restapis.shoppingcart.dto.UserProfileDto;
import org.restapis.shoppingcart.model.User;
import org.restapis.shoppingcart.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    //=============
    // User
    //=============
    UserDto createUser(UserDto userDto);
    UserProfileDto createUserProfile(UserProfileDto userProfile, String username) throws IllegalAccessException;
    UserDto getUserById(Long userId);
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
    List<User> getAllPosts();

    //=========
    // Account
    //=========

    AccountDto createAccount(AccountDto accountDto, Long id) throws IllegalAccessException;

    BigDecimal deposit(String iban, BigDecimal amount);

    BigDecimal withdraw(String iban, BigDecimal amount);

    //=============
    // UserProfile
    //=============
    UserProfileDto addUserProfile(long userId, UserProfileDto userProfileDto);

    Page<UserProfile> getAllUserProfileByUserId(Long userId, Pageable pageable);

    UserProfileDto updateUserProfile(long userId, long profileId, UserProfileDto userProfileDto);

    void deleteUserProfile(Long userId, Long id);
}
