package com.game.services;

import com.game.domain.Game;
import com.game.dto.NewGame;

/**
 * Created by antonioreuter on 16/07/17.
 */
public interface NewGameService {

    /**
     * Creates a new game
     * @param newGame
     * @return
     */
    Game newGame(NewGame newGame);
}
