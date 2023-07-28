package org.restapis.shoppingcart.mapper;

import org.restapis.shoppingcart.dto.UserProfileDto;
import org.restapis.shoppingcart.model.UserProfile;

public class UserProfileMapper {

    public static UserProfileDto  mapToUserProfileDto(UserProfile userProfile ){
        UserProfileDto userProfileDto = new UserProfileDto(
                userProfile.getId(), userProfile.getDescr(), userProfile.getNumber(), userProfile.getGender()
        );

        return userProfileDto;
    }

    public static UserProfile  mapToUserProfile(UserProfileDto userProfileDto ){
        UserProfile userProfile= new UserProfile(
                userProfileDto.getId(), userProfileDto.getDescr(), userProfileDto.getNumber(), userProfileDto.getGender()
        );

        return userProfile;
    }

}
