package com.marta.sandbox.microservices.shop.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException() {
        super("Username already exists", null, true, false);
    }
}
