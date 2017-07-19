package com.game.services.impl;

import com.game.domain.Game;
import com.game.domain.Pit;
import com.game.domain.Player;
import com.game.dto.GameMovement;
import com.game.exceptions.IllegalMovementException;
import com.game.exceptions.InvalidGameStateException;
import com.game.services.GameMovementService;
import com.game.services.PitService;
import com.game.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by antonioreuter on 16/07/17.
 */
@Service("gameMoveService")
public class GameMovementServiceImpl implements GameMovementService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PitService pitService;

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    public Pit move(final GameMovement movement) {
        int pitPos = movement.getIndex();
        final Player player = playerService.findPlayerByUserAndGame(movement.getLogin(), movement.getGameId());
        Game game = player.getGame();

        if (!game.isActive()) {
            throw new InvalidGameStateException("The game doesn't support any move at this moment.");
        }

        Pit currentPit = player.getPitByIndex(pitPos);

        if (player.lastPiece() && currentPit.isEmpty() && !currentPit.isBigPit()) {
            List<Pit> pits = pitService.findPitByGameAndIndex(game, pitPos);
            Pit otherPlayerPit = pits.stream().filter(pit -> !pit.getPlayer().equals(player)).findFirst().get();
            currentPit.addPieces(otherPlayerPit.removeAllPieces());
        }

        player.move(pitPos);

        if (player.finished()) {
            game.finishGame();
        }

        playerService.save(player);

        return currentPit;
    }
}
