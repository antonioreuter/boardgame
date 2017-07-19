package com.game.repositories;

import com.game.domain.Game;
import com.game.domain.GameStatus;
import com.game.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by antonioreuter on 16/07/17.
 */

@Repository("gameRepository")
public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

    /**
     * Finds all games by status
     * @param status
     * @return
     */
    List<Game> findAllByStatus(GameStatus status);
}
