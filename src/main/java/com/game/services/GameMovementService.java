package com.game.services;

import com.game.domain.Pit;
import com.game.dto.GameMovement;

/**
 * Created by antonioreuter on 16/07/17.
 */
public interface GameMovementService {

    /**
     * Executes a movement in the game
     * @param movement
     * @return
     */
    Pit move(final GameMovement movement);
}
