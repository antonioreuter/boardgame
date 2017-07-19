package com.game.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by antonioreuter on 16/07/17.
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidGameStatusException extends RuntimeException {

    public InvalidGameStatusException(String msg) {
        super(msg);
    }
}
