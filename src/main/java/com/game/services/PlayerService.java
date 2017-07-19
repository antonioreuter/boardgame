package com.game.services;

import com.game.domain.Game;
import com.game.domain.Player;
import com.game.domain.User;

/**
 * Created by antonioreuter on 16/07/17.
 */
public interface PlayerService {
    /**
     * Save or Update a player
     * @param player
     * @return Player
     */
    Player save(Player player);

    /**
     * Finds a player by id
     * @param id
     * @return Player
     */
    Player findById(Long id);

    /**
     * Join the player in a specific game id
     * @param login
     * @param gameId
     * @return Player
     */
    Player joinGame(String login, Long gameId);

    /**
     * Join the player in a specific game
     * @param user
     * @param game
     * @return Player
     */
    Player joinGame(User user, Game game);

    /**
     * Finds a player by login and game id
     * @param login
     * @param gameId
     * @return Player
     */
    Player findPlayerByUserAndGame(String login, Long gameId);

    /**
     * Finds a player by his user and game
     * @param user
     * @param game
     * @return
     */
    Player findPlayerByUserAndGame(User user, Game game);
}
