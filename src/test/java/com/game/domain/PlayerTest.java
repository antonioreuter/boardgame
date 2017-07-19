package com.game.domain;

import com.game.exceptions.IllegalMovementException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by antonioreuter on 18/07/17.
 */
public class PlayerTest {

    @Test
    public void testPlayerMovement() {
        User user1 = User.builder().id(10L).login("user1").name("User 1").build();
        User user2 = User.builder().id(20L).login("user2").name("User 2").build();
        Game game = new Game(user1, 3);

        List<Pit> pits1 = new ArrayList<>(3);
        pits1.add(Pit.builder().type(PitType.BIG).index(0).game(game).pieces(0).build());
        pits1.add(Pit.builder().type(PitType.REGULAR).index(1).game(game).pieces(0).build());
        pits1.add(Pit.builder().type(PitType.REGULAR).index(2).game(game).pieces(0).build());
        Player subject = new Player(user1, game, pits1);

        List<Pit> pits2 = new ArrayList<>(3);
        pits2.add(Pit.builder().type(PitType.BIG).index(0).game(game).pieces(0).build());
        pits2.add(Pit.builder().type(PitType.REGULAR).index(1).game(game).pieces(0).build());
        pits2.add(Pit.builder().type(PitType.REGULAR).index(2).game(game).pieces(0).build());
        Player secondPlayer = new Player(user2, game, pits2);

        game.joinPlayer(subject);
        game.joinPlayer(secondPlayer);



        subject.move(1);
        Pit pit = subject.getPitByIndex(1);

        assertEquals(1, pit.getPieces());
        assertEquals(2, subject.getPieces());
        assertEquals(1, subject.getCurrentRound());
    }

    @Test(expected = IllegalMovementException.class)
    public void testPlayerWhenTryToMoveAndGameIsNotReady() {
        User user = User.builder().id(10L).login("user").name("User").build();
        Game game = new Game(user, 3);

        Player subject = new Player(user, game);

        subject.move(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerWhenTryToMoveAnInvalidPit() {
        User user1 = User.builder().id(10L).login("user1").name("User 1").build();
        User user2 = User.builder().id(20L).login("user2").name("User 2").build();
        Game game = new Game(user1, 3);

        List<Pit> pits1 = new ArrayList<>(3);
        pits1.add(Pit.builder().type(PitType.BIG).index(0).game(game).pieces(0).build());
        pits1.add(Pit.builder().type(PitType.REGULAR).index(1).game(game).pieces(0).build());
        pits1.add(Pit.builder().type(PitType.REGULAR).index(2).game(game).pieces(0).build());
        Player subject = new Player(user1, game, pits1);

        List<Pit> pits2 = new ArrayList<>(3);
        pits2.add(Pit.builder().type(PitType.BIG).index(0).game(game).pieces(0).build());
        pits2.add(Pit.builder().type(PitType.REGULAR).index(1).game(game).pieces(0).build());
        pits2.add(Pit.builder().type(PitType.REGULAR).index(2).game(game).pieces(0).build());
        Player secondPlayer = new Player(user2, game, pits2);

        game.joinPlayer(subject);
        game.joinPlayer(secondPlayer);

        subject.move(4);
    }

    @Test
    public void testPlayerNextRound() {
        User user1 = User.builder().id(10L).login("user1").name("User 1").build();
        User user2 = User.builder().id(20L).login("user2").name("User 2").build();
        Game game = new Game(user1, 3);

        List<Pit> pits1 = new ArrayList<>(3);
        pits1.add(Pit.builder().type(PitType.BIG).index(0).game(game).pieces(0).build());
        pits1.add(Pit.builder().type(PitType.REGULAR).index(1).game(game).pieces(0).build());
        pits1.add(Pit.builder().type(PitType.REGULAR).index(2).game(game).pieces(0).build());
        Player subject = new Player(user1, game, pits1);

        List<Pit> pits2 = new ArrayList<>(3);
        pits2.add(Pit.builder().type(PitType.BIG).index(0).game(game).pieces(0).build());
        pits2.add(Pit.builder().type(PitType.REGULAR).index(1).game(game).pieces(0).build());
        pits2.add(Pit.builder().type(PitType.REGULAR).index(2).game(game).pieces(0).build());
        Player secondPlayer = new Player(user2, game, pits2);

        game.joinPlayer(subject);
        game.joinPlayer(secondPlayer);

        subject.move(1);
        Pit pit = subject.getPitByIndex(1);

        assertEquals(1, pit.getPieces());
        assertEquals(2, subject.getPieces());
        assertEquals(1, subject.getCurrentRound());

        subject.move(1);
        pit = subject.getPitByIndex(1);

        assertEquals(2, pit.getPieces());
        assertEquals(1, subject.getPieces());
        assertEquals(1, subject.getCurrentRound());


        assertTrue(subject.lastPiece());
        subject.move(1);
        pit = subject.getPitByIndex(1);

        assertEquals(3, pit.getPieces());
        assertEquals(3, subject.getPieces());
        assertEquals(2, subject.getCurrentRound());
    }
}
