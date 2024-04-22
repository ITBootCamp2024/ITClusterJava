package com.ua.itclusterjava2024.exceptions.handler;

import com.ua.itclusterjava2024.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException ex) {
        String errorMessage = "Помилка в базі даних: " + ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
