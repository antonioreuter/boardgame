package com.game.controllers.api.v1;

import com.game.domain.Player;
import com.game.services.PlayerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Slf4j
@RequestMapping(value = "/api/v1/players")
@RestController("playerController")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @ApiOperation(value = "Find a player by id", notes = "Find a player by id.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Player findPlayer(@PathVariable("id") Long id) {
        log.info("Loading player {}", id);
        return playerService.findById(id);
    }

    @ApiOperation(value = "Find a player by login and game id", notes = "Find a game by id.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}/{gameId}")
    public Player findPlayer(@PathVariable("login") String login, @PathVariable("gameId") Long gameId) {
        log.info("Find a player login:{}, game: {}", login, gameId);
        return playerService.findPlayerByUserAndGame(login, gameId);
    }

    @ApiOperation(value = "Join the user in a game", notes = "Join the user in a game based on his login and game id.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/joinGame/{login}/{gameId}")
    public Player joinGame(@PathVariable("login") String login, @PathVariable("gameId") Long gameId) {
        log.info("Join the user {} in the game {}", login, gameId);
        return playerService.joinGame(login, gameId);
    }
}
