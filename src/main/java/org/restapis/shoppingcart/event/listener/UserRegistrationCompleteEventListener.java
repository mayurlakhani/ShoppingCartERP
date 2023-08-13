package org.restapis.shoppingcart.event.listener;


import lombok.extern.slf4j.Slf4j;
import org.restapis.shoppingcart.model.User;
import org.restapis.shoppingcart.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UserRegistrationCompleteEventListener implements ApplicationListener<UserRegistrationCompleteEvent> {

    @Autowired
    private UserServiceImpl userService;
    @Override
    public void onApplicationEvent(UserRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveJwtToken(token, user);

        //Send Mail to user
        String url =
                event.getApplicationUrl()
                        + "/verifyRegistration?token="
                        + token;

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}",
                url);
    }
}
