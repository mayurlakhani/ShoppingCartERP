package org.restapis.shoppingcart.services;

import org.restapis.shoppingcart.dto.AccountDto;
import org.restapis.shoppingcart.dto.UserDto;
import org.restapis.shoppingcart.dto.UserProfileDto;
import org.restapis.shoppingcart.model.User;
import org.restapis.shoppingcart.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserProfileDto createUserProfile(UserProfileDto userProfile, String username) throws IllegalAccessException;
    UserDto getUserById(Long userId);
    UserDto updateUser(UserDto user);
    void deleteUser(Long userId);
    List<User> getAllPosts();

    AccountDto createAccount(AccountDto accountDto, Long id) throws IllegalAccessException;

    UserProfileDto addUserProfile(long userId, UserProfileDto userProfileDto);

    Page<UserProfile> findByUserId(Long userId, Pageable pageable);

    UserProfileDto updateUserProfile(long userId, long profileId, UserProfileDto userProfileDto);

    void findByUserIdAndId(Long userId, Long id);
}
