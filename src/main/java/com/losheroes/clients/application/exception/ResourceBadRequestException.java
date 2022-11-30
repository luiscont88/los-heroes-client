package com.losheroes.clients.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourceBadRequestException(String message) {
        super(message);
    }

}
