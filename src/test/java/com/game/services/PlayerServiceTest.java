package com.game.services;

import com.game.domain.Game;
import com.game.domain.GameStatus;
import com.game.domain.Player;
import com.game.domain.User;
import com.game.exceptions.InvalidGameStateException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.repositories.PlayerRepository;
import com.game.services.impl.PlayerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by antonioreuter on 18/07/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameService gameService;

    @Spy
    @InjectMocks
    private PlayerServiceImpl subject;

    @Test(expected = PlayerNotFoundException.class)
    public void testFindByIdWithInvalidId() {
        when(playerRepository.findOne(anyLong())).thenReturn(null);
        subject.findById(5L);
    }

    @Test(expected = InvalidGameStateException.class)
    public void testWhenTryToJoinAThirdPlayerInAGame() {
        User user1 = User.builder().id(1L).name("User 1").login("user1").build();
        User user2 = User.builder().id(2L).name("User 2").login("user2").build();
        User user3 = User.builder().id(3L).name("User 3").login("user3").build();
        Game game = new Game(user1, 5);

        when(playerRepository.save(any(Player.class))).thenReturn(new Player());

        subject.joinGame(user1, game);
        subject.joinGame(user2, game);
        subject.joinGame(user3, game);
    }

    @Test
    public void testWhenJoinTwoPlayersInAGame() {
        User user1 = User.builder().id(1L).name("User 1").login("user1").build();
        User user2 = User.builder().id(2L).name("User 2").login("user2").build();
        Game game = new Game(user1, 5);

        when(playerRepository.save(any(Player.class))).thenReturn(new Player());

        assertEquals(GameStatus.WAITING_PLAYER, game.getStatus());
        subject.joinGame(user1, game);
        assertEquals(GameStatus.WAITING_PLAYER, game.getStatus());
        subject.joinGame(user2, game);
        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
        assertTrue(game.isActive());
    }
}
