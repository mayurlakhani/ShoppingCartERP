package org.restapis.shoppingcart.controller;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.restapis.shoppingcart.dto.*;
import org.restapis.shoppingcart.enumerations.OrderStatus;
import org.restapis.shoppingcart.mapper.AccountMapper;
import org.restapis.shoppingcart.mapper.OrderMapper;
import org.restapis.shoppingcart.mapper.ProductMapper;
import org.restapis.shoppingcart.mapper.UserProfileMapper;
import org.restapis.shoppingcart.model.*;
import org.restapis.shoppingcart.repository.*;
import org.restapis.shoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path="/shoppingCart")
@Slf4j
public class UserController {
    @Autowired
    private OrderDeliveryStatusRepository orderDeliveryStatusRepository;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private OrderedItemsRepository orderedItemsRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userservice;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDtoResponse =  userservice.createUser(userDto);
        return new ResponseEntity<UserDto>(userDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<UserDto> getAllPosts() {

        return userservice.getAllPosts().stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(toList());
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
    //=================
    // User profile API
    //==================
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
    public ResponseEntity<String> deleteUserProfile(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "id") Long id) {
         userservice.deleteUserProfile(userId, id);
        return new ResponseEntity<>("profile deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/profile")
    public Page<UserProfile> getAllUserProfileByUserId(@PathVariable(value = "userId") Long userId,
                                                       Pageable pageable) {
         return userservice.getAllUserProfileByUserId(userId, pageable);
    }

   /* @PostMapping("/profile")
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfile, @RequestParam String username) throws IllegalAccessException {
        UserProfileDto userprofile =  userservice.createUserProfile(userProfile, username);
        return new ResponseEntity<>(userprofile, HttpStatus.CREATED);
    }*/

    //============
    // Account API
    //============
    @PostMapping("/profile/{profileId}/account")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto, @PathVariable(name = "profileId") Long id) throws IllegalAccessException {
        AccountDto accountDtoResponse =  userservice.createAccount(accountDto, id);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/{iban}/deposit")
    public BigDecimal deposit(@PathVariable String iban, @RequestParam BigDecimal amount) {
        return userservice.deposit(iban, amount);
    }

    @PostMapping("/{iban}/withdraw")
    public BigDecimal withdraw(@PathVariable String iban, @RequestParam BigDecimal amount) {
        return userservice.withdraw(iban, amount);
    }

    @GetMapping("/profile/account/{accountId}")
    public Page<UserProfile> GetAllAccountByUser(@PathVariable(name = "accountId") long accountId, Pageable pageable){
        return userProfileRepository.findByAccountId(accountId, pageable);
    }

    @PutMapping("/profile/account/{accountId}")
    public UserProfileDto updateAccount(@RequestBody AccountDto accountDto, @PathVariable(name = "accountId") Long id, Pageable pageable){
            Optional<UserProfile> userProfile  = userProfileRepository.findByAccountId(id);
            Optional<Account> account = accountRepository.findById(id);
            Account accountReq = account.get();

            Account accountRes = AccountMapper.mapToAccount(accountDto);
            accountRes.setId(id);
            accountReq.setBankName(accountRes.getBankName());
            accountReq.setAccountHolderName(accountRes.getAccountHolderName());
            accountReq.setBIC(accountRes.getBIC());
            accountReq.setIBAN(accountRes.getIBAN());
            accountReq.setPassword(accountRes.getPassword());
            accountReq.setId(id);
            log.info("accountReq ::" + accountReq);
            accountRepository.save(accountReq);
            userProfile.get().setAccount(accountReq);

            userProfileRepository.save(userProfile.get());
            UserProfileDto userProfile1 = UserProfileMapper.mapToUserProfileDto(userProfile.get());
            userProfile1.setAccount(accountRes);
            userProfile1.setUser(userProfile.get().getUser());
            log.info("userProfile Found::"+ userProfile1);
            return userProfile1;
    }

    @DeleteMapping("/profile/account/{accountId}")
    public UserProfileDto deleteAccount(@PathVariable(name = "accountId") long id){
        Optional<UserProfile> userProfile  = userProfileRepository.findByAccountId(id);
        Optional<Account> account = accountRepository.findById(userProfile.get().getAccount().getId());
        accountRepository.delete(account.get());

        UserProfileDto userProfileDto = UserProfileMapper.mapToUserProfileDto(userProfile.get());
        return userProfileDto;
    }

    //===============
    // Adding product
    //===============

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto){
        Product product = ProductMapper.mapToProduct(productDto);
        productRepository.save(product);
        return new ResponseEntity<>("product created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        Product product = ProductMapper.mapToProduct(productDto);
        Optional<Product> re = productRepository.findById(product.getId());
        re.get().setId(product.getId());
        re.get().setName(product.getName());
        re.get().setDescription(product.getDescription());
        re.get().setPrice(product.getPrice());

        productRepository.save(re.get());
        ProductDto productDto1 = ProductMapper.mapToProductDto(re.get());
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id){
        Optional<Product> response = productRepository.findById(id);
        productRepository.delete(response.get());
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    //=========================
    // Order create with user
    //=========================
    @PostMapping("/order/{userId}/")
    public ResponseEntity<String> createOrder(@PathVariable(name="userId") long id, @RequestBody List<OrderedItemsDto> orderedItemsDto){
        Optional<User> user = userRepository.findById(id);
        Order order = new Order();
        order.setOrder_number(UUID.randomUUID().toString());
        Float total_price = (float) 0;
        List<OrderedItems> orderedItems = orderedItemsDto.stream().map(i-> OrderMapper.mapToOrderedItems((OrderedItemsDto) i)).toList();
        orderedItems.stream().map(i -> total_price + i.getPrice()).collect(toList());
        log.info("orderItem::"+ orderedItems);
        order.setTotalPrice(total_price);
        log.info("total price::"+total_price);
        order.setOrderedItemsList(orderedItems);
        order.setUser(user.get());
        return (ResponseEntity<String>) getPlacedOrder(order);
    }

    @PutMapping("/order/{userId}/{order_number}")
    public ResponseEntity<String> updateOrder(@PathVariable(name="userId") long userId, @PathVariable(name = "order_number") String order,
                                         @RequestBody List<OrderedItemsDto> orderedItemsDto){
        Order order1 = orderRepository.findByUserIdAndOrderNumber(userId, order);
        List<OrderedItems> orderedItems = orderedItemsDto.stream().map(i -> OrderMapper.mapToOrderedItems((OrderedItemsDto) i)).toList();
        log.info("orderItem::"+ orderedItems);
        order1.setOrderedItemsList(orderedItems);
        order1.setUser(order1.getUser());

        // withdraw from the account
        UserProfile userProfile = userProfileRepository.findUserByUserId(userId);
        Optional<Account> account = accountRepository.findById(userProfile.getAccount().getId());

        userservice.withdraw(account.get().getIBAN(), BigDecimal.valueOf(500));

        return getPlacedOrder(order1);
    }

    private ResponseEntity<String> getPlacedOrder(Order order1) {
        List<String> skuCodes = order1.getOrderedItemsList().stream()
                .map(OrderedItems::getSkuCode).toList();
        log.info("skuCodes::" + skuCodes);
        List<OrderedItems> orderedItemsList = skuCodes.stream().map(i -> (OrderedItems) orderedItemsRepository.findBySkuCode(i)).toList();
        order1.setOrderedItemsList(orderedItemsList);
        log.info("Order::"+order1);
        orderRepository.save(order1);

        // create OrderDeliveryStatus
        OrderDeliveryStatus orderDeliveryStatus = OrderDeliveryStatus.builder()
                .Status(OrderStatus.CREATED)
                .order(order1)
                .build();

        // create transaction for payment
        Transactions transaction = Transactions.builder()
                .transactionId(UUID.randomUUID().toString())
                .totalAmount(order1.getTotalPrice())   // cut from vault   :: order1.getTotalPrice() - vault.getTotalAmount()
                .orderDeliveryStatus(orderDeliveryStatus)
                .build();
        transactionsRepository.save(transaction);
        return new ResponseEntity<>("created order successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/order/{userId}/{order_number}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "userId") long userId, @PathVariable(name = "order_number") String order){
        Order order1 = orderRepository.findByUserIdAndOrderNumber(userId, order);
        OrderDeliveryStatus orderDeliveryStatus = orderDeliveryStatusRepository.findByOrderId(order1.getId());
        orderDeliveryStatus.setStatus(OrderStatus.CANCELED);
        orderDeliveryStatusRepository.delete(orderDeliveryStatus);
        orderRepository.delete(order1);
        return new ResponseEntity<>("deleted order successfully", HttpStatus.OK);
    }

    @GetMapping("/orders")
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

}
