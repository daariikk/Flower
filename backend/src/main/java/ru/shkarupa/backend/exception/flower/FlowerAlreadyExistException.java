package ru.shkarupa.backend.exception.flower;

import ru.shkarupa.backend.exception.ApiError;

public class FlowerAlreadyExistException extends ApiError {
    private static final int code = 400;
    private static final String message = "Car already exists";
    private static final String description = "You are trying to add Car with already existing name";

    public FlowerAlreadyExistException() {
        super(code, message, description);
    }
}
