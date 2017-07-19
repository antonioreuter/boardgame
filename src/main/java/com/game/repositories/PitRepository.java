package com.game.repositories;

import com.game.domain.Game;
import com.game.domain.Pit;
import com.game.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Repository("pitRepository")
public interface PitRepository extends CrudRepository<Pit, Long> {
    /**
     * Finds all pits by game and index
     * @param game
     * @param index
     * @return
     */
    List<Pit> findPitByGameAndIndex(final Game game, final Integer index);

    /**
     * Finds all pits by player and index
     * @param player
     * @param index
     * @return
     */
    Pit findPitByPlayerAndIndex(final Player player, final Integer index);
}
