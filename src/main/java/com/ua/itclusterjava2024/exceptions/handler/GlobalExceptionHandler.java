package com.ua.itclusterjava2024.exceptions.handler;

import com.ua.itclusterjava2024.dto.response.MessageResponse;
import com.ua.itclusterjava2024.dto.response.JwtErrorResponse;
import com.ua.itclusterjava2024.exceptions.JwtTokenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<MessageResponse> handleSQLException(SQLException ex) {
        String errorMessage = "Помилка в базі даних: " + ex.getMessage();
        return new ResponseEntity<>(new MessageResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<JwtErrorResponse> handleJwtTokenException(JwtTokenException ex) {
        return new ResponseEntity<>(new JwtErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new MessageResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
