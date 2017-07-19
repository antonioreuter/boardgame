package com.game.services.impl;

import com.game.domain.Game;
import com.game.domain.Pit;
import com.game.domain.Player;
import com.game.repositories.PitRepository;
import com.game.services.PitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by antonioreuter on 17/07/17.
 */
@Service("pitService")
public class PitServiceImpl implements PitService {

    @Autowired
    private PitRepository pitRepository;

    @Override
    public List<Pit> findPitByGameAndIndex(final Game game, final Integer index) {
        return pitRepository.findPitByGameAndIndex(game, index);
    }

    @Override
    public Pit findPitByPlayerAndIndex(final Player player, final Integer index) {
        return pitRepository.findPitByPlayerAndIndex(player, index);
    }

    @Override
    @Transactional
    public Pit save(Pit pit) {
        return pitRepository.save(pit);
    }
}
