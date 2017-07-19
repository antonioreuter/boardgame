package com.game.services;

import com.game.domain.Game;
import com.game.domain.Pit;
import com.game.domain.Player;

import java.util.List;

/**
 * Created by antonioreuter on 17/07/17.
 */
public interface PitService {
    /**
     * Finds a pit by game and index
     * @param game
     * @param index
     * @return
     */
    List<Pit> findPitByGameAndIndex(final Game game, final Integer index);


    /**
     * Finds a pit by player and index
     * @param player
     * @param index
     * @return
     */
    Pit findPitByPlayerAndIndex(final Player player, final Integer index);

    /**
     * Save or Update a pit
     * @param pit
     * @return
     */
    Pit save(Pit pit);
}
