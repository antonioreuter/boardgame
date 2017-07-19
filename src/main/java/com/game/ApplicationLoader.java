package com.game;

import com.game.domain.User;
import com.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by antonioreuter on 18/07/17.
 */
@Component("applicationLoader")
public class ApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User user1 = User.builder().name("User 1").login("user1").build();
        User user2 = User.builder().name("User 2").login("user2").build();
        User user3 = User.builder().name("User 3").login("user3").build();

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
    }
}
