package org.restapis.shoppingcart.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.restapis.shoppingcart.dto.AccountDto;
import org.restapis.shoppingcart.dto.UserDto;
import org.restapis.shoppingcart.dto.UserProfileDto;
import org.restapis.shoppingcart.mapper.AccountMapper;
import org.restapis.shoppingcart.mapper.UserMapper;
import org.restapis.shoppingcart.mapper.UserProfileMapper;
import org.restapis.shoppingcart.model.Account;
import org.restapis.shoppingcart.model.User;
import org.restapis.shoppingcart.model.UserProfile;
import org.restapis.shoppingcart.repository.AccountRepository;
import org.restapis.shoppingcart.repository.UserProfileRepository;
import org.restapis.shoppingcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;


    @Autowired
    private AccountRepository accountRepository;

    public UserDto createUser(UserDto userDto) {
        log.info("USerDto::"+userDto);

       /* User user = User.builder().username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getPassword())
                .build();*/

        User user = UserMapper.mapToUser(userDto);
        userRepository.save(user);
        UserDto savedUserDto = UserMapper.mapToUserDto(user);

        /*UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUsername(userDto.getUsername());
        userDtoResponse.setEmail(userDto.getEmail());*/
        return savedUserDto;
    }

    public UserProfileDto createUserProfile(UserProfileDto userProfile, String username) throws IllegalAccessException {

        // UserProfile Dto map to user profile
        UserProfile userProfile1 = UserProfileMapper.mapToUserProfile(userProfile);

        // find user object by name
        List<User> usr = userRepository.findByUserNameEquals(username);
        if(usr == null){
            throw new IllegalAccessException();
        }
        else {
            usr.stream().forEach(i -> { userProfile1.setUser(i);});  // set the userprofile to each user by name
        }

        // save userprofile
        userProfileRepository.save(userProfile1);

        // time to return dto as response
        UserProfileDto savedUserProfile = UserProfileMapper.mapToUserProfileDto(userProfile1);

        // set user to userprofile dto
        usr.stream().forEach(i -> { savedUserProfile.setUser(i);});
        return savedUserProfile;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto, Long id) throws IllegalAccessException{
        log.info("accountDto::"+accountDto);
        Account account = AccountMapper.mapToAccount(accountDto);
        log.info("account obj::"+account);
        // find user object by name
        UserProfile usr = (UserProfile) userProfileRepository.findUserByUserName(id);
        log.info("user::"+ usr.getId());
        if(usr == null){
            throw new IllegalAccessException();
        }
        else {
            usr.setAccount(account);
            userProfileRepository.save(usr);// set the userprofile to each user by name
        }

        // save userprofile
        accountRepository.save(account);

        AccountDto accountDto1 = AccountMapper.mapToAccountDto(account);
        return accountDto1;
    }

    public UserDto getUserById(Long userId) {
        Optional<User> user =  userRepository.findById(userId);
        User respuser = user.get();
        return UserMapper.mapToUserDto(respuser);
    }

    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }



    public List<User> getAllPosts() {
        return userRepository.findAll();
    }


}
