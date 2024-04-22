package com.ua.itclusterjava2024.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EmailNotConfirmedException extends AccountStatusException {
    public EmailNotConfirmedException(String msg) {
        super(msg);
    }
}