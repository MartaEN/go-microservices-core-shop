package com.marta.sandbox.microservices.shop.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class IllegalActionException extends RuntimeException {

    public IllegalActionException(String message) {
        super(message, null, true, false);
    }
}
