package ru.shkarupa.backend.exception.flower;

import ru.shkarupa.backend.exception.ApiError;

public class FlowerNotFoundAtUserException extends ApiError {
    private static final int code = 400;
    private static final String message = "Car not found at User";
    private static final String description = "Car not found at User";

    public FlowerNotFoundAtUserException() {
        super(code, message, description);
    }
}
