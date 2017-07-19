package com.game.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by antonioreuter on 16/07/17.
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class IllegalMovementException extends RuntimeException {

    public IllegalMovementException(String msg) {
        super(msg);
    }

    public IllegalMovementException(String msg, Throwable th) {
        super(msg, th);
    }
}
