package com.game.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by antonioreuter on 17/07/17.
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class InvalidGameStateException extends RuntimeException {

    public InvalidGameStateException(String message) {
        super(message);
    }
}
