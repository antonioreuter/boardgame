package com.game.controllers.api.v1;

import com.game.domain.Game;
import com.game.domain.GameStatus;
import com.game.domain.Player;
import com.game.dto.NewGame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by antonioreuter on 18/07/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreatePlayer() {
        String userLogin = "user1";
        NewGame newGame = new NewGame(userLogin, 5);
        ResponseEntity<Game> gameResponse = this.restTemplate.postForEntity("/api/v1/games/new", newGame, Game.class);
        Game game = gameResponse.getBody();

        assertEquals(HttpStatus.CREATED, gameResponse.getStatusCode());
        assertEquals(5, game.getRounds());
        assertEquals(GameStatus.WAITING_PLAYER, game.getStatus());
        assertEquals(userLogin, game.getFirstPlayer().getUser().getLogin());
    }


    @Test
    public void testJoinGame() {
        String userLogin = "user1";
        NewGame newGame = new NewGame(userLogin, 5);
        ResponseEntity<Game> gameResponse = this.restTemplate.postForEntity("/api/v1/games/new", newGame, Game.class);
        Game game = gameResponse.getBody();

        String userLogin2 = "user2";
        ResponseEntity<Player> playerResponse = this.restTemplate.getForEntity("/api/v1/players/joinGame/{userLogin}/{gameId}", Player.class, userLogin2, game.getId());
        Player player2 = playerResponse.getBody();

        assertEquals(HttpStatus.OK, playerResponse.getStatusCode());
        assertEquals(userLogin2, player2.getUser().getLogin());
    }

    private HttpHeaders customHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    @TestConfiguration
    static class Config {
        @Value("${api.username}")
        private String username;

        @Value("${api.password}")
        private String password;

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder()
                    .basicAuthorization(username, password);
        }
    }
}
