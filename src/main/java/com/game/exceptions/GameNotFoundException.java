package com.game.exceptions;

/**
 * Created by antonioreuter on 18/07/17.
 */
public class GameNotFoundException extends ResourceNotFoundException {

    public GameNotFoundException(String msg) {
        super(msg);
    }
}
