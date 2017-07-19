package com.game.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by antonioreuter on 18/07/17.
 */
public class GameTest {

    @Test
    public void testJoinPlayer() {
        int rounds = 3;
        User user1 = User.builder().login("user1").name("User 1").build();
        User user2 = User.builder().login("user2").name("User 2").build();
        Game subject = new Game(user1, rounds);
        Player player1 = Player.builder().user(user1).game(subject).build();
        subject.joinPlayer(player1);

        Assert.assertEquals(GameStatus.WAITING_PLAYER, subject.getStatus());

        Player player2 = Player.builder().user(user2).game(subject).build();
        subject.joinPlayer(player2);

        Assert.assertEquals(GameStatus.IN_PROGRESS, subject.getStatus());
        Assert.assertEquals(true, subject.isActive());
    }

}
