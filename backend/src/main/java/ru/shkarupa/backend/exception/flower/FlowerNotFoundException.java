package ru.shkarupa.backend.exception.flower;

import ru.shkarupa.backend.exception.ApiError;

public class FlowerNotFoundException extends ApiError {
    private static final int code = 404;
    private static final String message = "Car Not Found";
    private static final String description = "Car with such id was not found";

    public FlowerNotFoundException() {
        super(code, message, description);
    }
}
