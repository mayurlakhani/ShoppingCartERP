package org.restapis.shoppingcart.mapper;

import org.restapis.shoppingcart.dto.UserDto;
import org.restapis.shoppingcart.model.User;

public class UserMapper {


    // Convert User JPA Entity into UserDto
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
        return userDto;
    }
    // Convert UserDto into User JPA Entity
    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail()
        );
        return user;
    }


}
