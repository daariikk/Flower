package ru.shkarupa.backend.exception.flower;

import ru.shkarupa.backend.exception.ApiError;

public class FlowerAlreadyExistAtUserException extends ApiError {
    private static final int code = 400;
    private static final String message = "Car already exist at user";
    private static final String description = "Car already exist at user";

    public FlowerAlreadyExistAtUserException() {
        super(code, message, description);
    }
}
