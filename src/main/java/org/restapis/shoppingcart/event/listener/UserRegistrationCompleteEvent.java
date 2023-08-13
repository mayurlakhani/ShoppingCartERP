package org.restapis.shoppingcart.event.listener;


import lombok.Getter;
import lombok.Setter;
import org.restapis.shoppingcart.model.User;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class UserRegistrationCompleteEvent extends ApplicationEvent {

    private final User user;
    private final String applicationUrl;

    public UserRegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this. user = user;
        this.applicationUrl = applicationUrl;
    }

}
