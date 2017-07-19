package com.game.repositories;

import com.game.domain.Game;
import com.game.domain.Player;
import com.game.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Repository("playerRepository")
public interface PlayerRepository extends CrudRepository<Player, Long> {

    /**
     * Find player by user and game
     * @param user
     * @param game
     * @return
     */
    Player findPlayerByUserAndGame(User user, Game game);
}
