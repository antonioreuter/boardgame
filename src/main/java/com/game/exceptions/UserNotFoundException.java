package com.game.exceptions;

/**
 * Created by antonioreuter on 18/07/17.
 */
public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
