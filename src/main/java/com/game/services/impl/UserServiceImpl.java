package com.game.services.impl;

import com.game.domain.User;
import com.game.exceptions.ResourceNotFoundException;
import com.game.exceptions.UserNotFoundException;
import com.game.repositories.UserRepository;
import com.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByLogin(String login) {
        User user = userRepository.findUserByLogin(login);

        if (user == null) {
            throw new UserNotFoundException("There is no user with this login: "+ login);
        }


        return user;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
