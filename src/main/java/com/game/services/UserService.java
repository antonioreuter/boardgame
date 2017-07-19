package com.game.services;

import com.game.domain.User;

/**
 * Created by antonioreuter on 16/07/17.
 */
public interface UserService {
    /**
     * Finds a user by login
     * @param login
     * @return User
     */
    User findByLogin(String login);

    /**
     * Create or Update a user
     * @param user
     * @return User
     */
    User save(User user);
}
