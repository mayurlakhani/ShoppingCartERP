package org.restapis.shoppingcart.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.restapis.shoppingcart.dto.AccountDto;
import org.restapis.shoppingcart.dto.UserDto;
import org.restapis.shoppingcart.dto.UserProfileDto;
import org.restapis.shoppingcart.exception.ResourceNotFoundException;
import org.restapis.shoppingcart.mapper.AccountMapper;
import org.restapis.shoppingcart.mapper.UserMapper;
import org.restapis.shoppingcart.mapper.UserProfileMapper;
import org.restapis.shoppingcart.model.*;
import org.restapis.shoppingcart.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final VerificationTokenRepository verificationTokenRepository;
    private final VaultRepository vaultRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;


    @Autowired
    private AccountRepository accountRepository;

    public List<User> getAllPosts() {
        return userRepository.findAll();
    }

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
    public UserProfileDto addUserProfile(long userId, UserProfileDto userProfileDto) {
        UserProfile userProfile = UserProfileMapper.mapToUserProfile(userProfileDto);
        Optional<User> user = userRepository.findById(userId);
       // User update = user.get();
        userProfile.setUser(user.get());
        userProfileRepository.save(userProfile);
        UserProfileDto response = UserProfileMapper.mapToUserProfileDto(userProfile);
        response.setUser(user.get());
        return response;
    }

    @Override
    public Page<UserProfile> getAllUserProfileByUserId(Long userId, Pageable pageable) {
        return userProfileRepository.findByUserId(userId, pageable);

    }

    @Override
    public UserProfileDto updateUserProfile(long userId, long profileId, UserProfileDto userProfileDto) {
        UserProfile userProfile = UserProfileMapper.mapToUserProfile(userProfileDto);
        userProfile.setId(profileId);
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }

        Optional<Account> account = accountRepository.findById(userId);
        Optional<User> user = userRepository.findById(userId);
        Optional<UserProfile> usrProfile = userProfileRepository.findById(profileId);
        usrProfile.get().setId(profileId);
        usrProfile.get().setDescr(userProfile.getDescr());
        usrProfile.get().setNumber(userProfile.getNumber());
        usrProfile.get().setGender(userProfile.getGender());
        usrProfile.get().setAccount(account.get());
        usrProfile.get().setUser(user.get());
        UserProfileDto response = UserProfileMapper.mapToUserProfileDto(userProfile);
        //response.setId(profileId);
        response.setUser(user.get());
        return response;
    }

    @Override
    public void deleteUserProfile(Long userId, Long id) {
        UserProfile userProfile =userProfileRepository.findByIdAndUserId(userId, id);
        userProfileRepository.delete(userProfile);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto, Long id) throws IllegalAccessException{
        log.info("accountDto::"+ accountDto);
        Account account = AccountMapper.mapToAccount(accountDto);
        log.info("account obj::"+ account);
        // find user object by name
        UserProfile usr = (UserProfile) userProfileRepository.findUserByUserId(id);
        log.info("user::"+ usr.getId());

        Vault vault = Vault.builder()
                .credit(BigDecimal.ZERO)
                .account(account)
                .build();
        if(usr == null){
            throw new IllegalAccessException();
        }
        else {
            usr.setAccount(account);
            vaultRepository.save(vault);
            userProfileRepository.save(usr);// set the userprofile to each user by name
        }
        // save userprofile
        accountRepository.save(account);

        AccountDto accountDto1 = AccountMapper.mapToAccountDto(account);
        return accountDto1;
    }

    public BigDecimal deposit(String iban, BigDecimal amount) {
        Account account = accountRepository.findByIBAN(iban);
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
        return newBalance;
    }

    public BigDecimal withdraw(String iban, BigDecimal amount) {
        Account account = accountRepository.findByIBAN(iban);
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.subtract(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
        return newBalance;
    }

    public void saveJwtToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    public String validateVerificationToken(String token) {
        VerificationToken verificationToken= verificationTokenRepository.findByToken(token);
        if(verificationToken ==null){
            return "invalid";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }
        //user enabled
        //user.setEnabled();
        //userRepository.save(user); // save boolean activated value
        return "valid";
    }

    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }
}
