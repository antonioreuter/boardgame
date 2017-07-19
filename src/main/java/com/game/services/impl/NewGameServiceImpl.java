package com.game.services.impl;

import com.game.domain.Game;
import com.game.domain.Player;
import com.game.domain.User;
import com.game.dto.NewGame;
import com.game.services.GameService;
import com.game.services.NewGameService;
import com.game.services.PlayerService;
import com.game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Service("newGameService")
public class NewGameServiceImpl implements NewGameService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional()
    public Game newGame(NewGame newGame) {
        User user = userService.findByLogin(newGame.getLogin());
        Game game = gameService.createGame(user, newGame.getRounds());
        Player player = playerService.joinGame(user, game);

        return game;
    }
}
