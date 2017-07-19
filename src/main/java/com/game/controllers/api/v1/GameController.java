package com.game.controllers.api.v1;

import com.game.domain.Game;
import com.game.dto.GameMovement;
import com.game.dto.NewGame;
import com.game.services.GameMovementService;
import com.game.services.GameService;
import com.game.services.NewGameService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Slf4j
@RequestMapping(value = "/api/v1/games")
@RestController("gameController")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private NewGameService newGameService;

    @Autowired
    private GameMovementService gameMovementService;


    @ApiOperation(value = "Create new game", notes = "Allows a user to create a new game. It's possible to determine the number of rounds.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new")
    public Game newGame(@Valid @RequestBody NewGame newGame) {
        log.info("Create a new game: {}", newGame);

        Game game = newGameService.newGame(newGame);
        return game;
    }

    //TODO - The pagination is not implemented.
    @ApiOperation(value = "List games by status", notes = "List all games based on status.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/status/{status}")
    public List<Game> listGamesByStatus(@PathVariable("status") String status) {
        log.info("Listing games by status: {}", status);
        return gameService.listGamesByStatus(status);
    }

    @ApiOperation(value = "Find a game by id", notes = "Find a game by id.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/{id}")
    public Game loadGame(@PathVariable("id") Long gameId) {
        log.info("Loading the game: {}", gameId);
        return gameService.loadGame(gameId);
    }
}
