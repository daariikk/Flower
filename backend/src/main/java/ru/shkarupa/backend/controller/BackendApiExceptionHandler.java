package ru.shkarupa.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shkarupa.backend.exception.flower.FlowerAlreadyExistAtUserException;
import ru.shkarupa.backend.exception.flower.FlowerAlreadyExistException;
import ru.shkarupa.backend.exception.flower.FlowerNotFoundException;
import ru.shkarupa.backend.dto.exception.ApiErrorResponse;
import ru.shkarupa.backend.exception.flower.FlowerNotFoundAtUserException;
import ru.shkarupa.backend.exception.user.UserIsAlreadyExistException;
import ru.shkarupa.backend.exception.user.UserNotFoundException;

@RestControllerAdvice
public class BackendApiExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> userAlreadyExist(UserIsAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> userNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.toApiErrorResponse());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> carAlreadyExist(FlowerAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> carNotFound(FlowerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> carAlreadyExistsAtUser(FlowerAlreadyExistAtUserException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> carNotFoundAtUser(FlowerNotFoundAtUserException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.toApiErrorResponse());
    }
}