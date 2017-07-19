package com.game.services.impl;

import com.game.domain.Game;
import com.game.domain.GameStatus;
import com.game.domain.Player;
import com.game.domain.User;
import com.game.exceptions.InvalidGameStateException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.exceptions.ResourceNotFoundException;
import com.game.repositories.PlayerRepository;
import com.game.services.GameService;
import com.game.services.PlayerService;
import com.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player findById(Long id) {
        Player player = playerRepository.findOne(id);

        if (player == null) {
            throw new PlayerNotFoundException("There is no player with this id: "+ id);
        }

        return player;
    }

    @Override
    @Transactional
    public Player joinGame(String login, Long gameId) {
        User user = userService.findByLogin(login);
        Game game = gameService.loadGame(gameId);
        return joinGame(user, game);
    }

    @Override
    @Transactional
    public Player joinGame(User user, Game game) {
        Assert.notNull(user, "You need specify a valid user.");

        if (game.getStatus() != GameStatus.WAITING_PLAYER) {
            throw new InvalidGameStateException("The game is already in progress. Look for another available game.");
        }

        Player player = new Player(user, game);

        game.joinPlayer(player);

        player = playerRepository.save(player);

        return player;
    }

    @Override
    public Player findPlayerByUserAndGame(String login, Long gameId) {
        User user = userService.findByLogin(login);
        Game game = gameService.loadGame(gameId);

        return findPlayerByUserAndGame(user, game);
    }

    @Override
    public Player findPlayerByUserAndGame(User user, Game game) {
        return playerRepository.findPlayerByUserAndGame(user, game);
    }
}
