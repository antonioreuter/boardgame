# Web API - Board Game

A Web Api to support a board game.

#### Technologies
- Gradle
- JDK 1.8
- Spring Boot
- Spring-Web
- Mockito
- Junit
- H2 (Database)

### Installation
#### Requirements
- JDK 1.8

Executing the command below, it'll install all the project dependencies and build the package.

```
    ./gradlew build
```

### Running

```
    java -jar build/libs/gameBoard-1.0-SNAPSHOT.jar
```

### Documentation

```
    http://localhost:8080/swagger-ui.html#/
```

### Default users
1. There are some users already created. Just for test purpose. They are: `user1`, `user2` and `user3`.



### How to start a new game

1. First of all you need to be authenticated before use the Web Api. The application is using the **Basic Auth** authentication.

    ```
        Basic Auth: api:123456
    ```

2. Second of all, you need to create a new game. You need to specify a valid user and the number of rounds.
    ```
        [POST] http://localhost:8080/api/v1/games/new

        [Payload]
        {
        	"login": "user1",
        	"rounds": 3
        }
    ```

3. Now we need to join another user before start the game.

    1. To join the second user in the game, you need to follow the procedures below.

        ```
            [GET] http://localhost:8080/api/v1/players/joinGame/{user_id}/{game_id}
        ```

4. To perform a movement you need to specify a player, the game and the position of the pit, where you want to place your stone.

    ```
        [POST] http://localhost:8080/api/v1/game/move/

        [Payload]
        {
        	"login": "{user_login}",
        	"gameId": 1,
        	"index": 5
        }
    ```

5. Suggestions to improve the application:
    * Add WebSockets
    * Build a web interface. 
    * Create a screen to create new users.
    * Build an authentication page.
    * Build a first page where the users can see all available games.
    * Create a websocket connection when the user join into a game.
    