package com.game.exceptions;

/**
 * Created by antonioreuter on 18/07/17.
 */
public class PlayerNotFoundException extends ResourceNotFoundException {

    public PlayerNotFoundException(String msg) {
        super(msg);
    }
}
