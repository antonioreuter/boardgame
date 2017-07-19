package com.game.services;

import com.game.domain.Game;
import com.game.domain.Player;
import com.game.domain.User;

import java.util.List;

/**
 * Created by antonioreuter on 16/07/17.
 */
public interface GameService {
    /**
     * Creates a new game and determine the number of rounds
     * @param user
     * @param rounds
     * @return Game
     */
    Game createGame(User user, int rounds);

    /**
     * Loads a game by id
     * @param gameId
     * @return Game
     */
    Game loadGame(Long gameId);

    /**
     * List all the games based on status
     * @param status
     * @return
     */
    List<Game> listGamesByStatus(String status);

    /**
     * Finish the game
     * @param game
     */
    void finish(Game game);
}
