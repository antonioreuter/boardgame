package com.game.domain;

import lombok.*;

/**
 * Created by antonioreuter on 16/07/17.
 */
@ToString(of = {"type"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PitType {

    BIG("Big Pit"), REGULAR("Regular Pit");

    @Getter
    @Setter(AccessLevel.NONE)
    private String type;
}
