package com.game.repositories;

import com.game.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a user by login
     * @param login
     * @return
     */
    User findUserByLogin(String login);
}
