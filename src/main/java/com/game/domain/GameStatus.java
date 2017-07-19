package com.game.domain;

import lombok.*;

/**
 * Created by antonioreuter on 16/07/17.
 */
@ToString(of = {"status"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GameStatus {

    WAITING_PLAYER("Waiting for a player"), IN_PROGRESS("In Progress"), FINISHED("Finished"), CANCELED("Canceled");

    @Getter
    @Setter(AccessLevel.NONE)
    private String status;

}
