package com.game.services.impl;

import com.game.domain.Game;
import com.game.domain.GameStatus;
import com.game.domain.Player;
import com.game.domain.User;
import com.game.exceptions.GameNotFoundException;
import com.game.exceptions.InvalidGameStatusException;
import com.game.exceptions.ResourceNotFoundException;
import com.game.repositories.GameRepository;
import com.game.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by antonioreuter on 16/07/17.
 */
@Service("gameService")
public class GameServiceImpl implements GameService{

    @Autowired
    private GameRepository gameRepository;



    @Override
    @Transactional
    public Game createGame(User user, int rounds) {
        Game game = new Game(user, rounds);

        return gameRepository.save(game);
    }

    @Override
    @Transactional
    public void finish(Game game) {
        game = loadGame(game.getId());
        game.finishGame();
        gameRepository.save(game);
    }

    @Override
    public Game loadGame(Long gameId) {
        Game game = gameRepository.findOne(gameId);
        if (game == null) {
            throw new GameNotFoundException("There is no game with this id: " + gameId);
        }

        return game;
    }

    @Override
    public List<Game> listGamesByStatus(String status) {
        try {
            GameStatus gameStatus = GameStatus.valueOf(status);
            return gameRepository.findAllByStatus(gameStatus);
        } catch (IllegalArgumentException ex) {
            throw new InvalidGameStatusException("Invalid status: " + status);
        }
    }
}
