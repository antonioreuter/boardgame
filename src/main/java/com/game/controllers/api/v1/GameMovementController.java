package com.game.controllers.api.v1;

import com.game.domain.Pit;
import com.game.dto.GameMovement;
import com.game.services.GameMovementService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by antonioreuter on 17/07/17.
 */
@Slf4j
@RequestMapping(value="/api/v1/game/move")
@RestController("gameMovementController")
public class GameMovementController {

    @Autowired
    private GameMovementService gameMovementService;

    @ApiOperation(value = "Executes a movement in the game.",
            notes = "Executes a movement in the game based on a player and position of his pit.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public Pit movement(@Valid @RequestBody GameMovement gameMovement) {
        log.info("Executing a movement: {}", gameMovement);
        return gameMovementService.move(gameMovement);
    }
}
