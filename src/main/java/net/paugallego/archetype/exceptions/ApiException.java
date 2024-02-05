package net.paugallego.archetype.exceptions;

public class ApiException extends Exception {
    private final ApiErrorEnum apiError;

    public ApiException(ApiErrorEnum apiError) {
        super();
        this.apiError = apiError;
    }

    public ApiErrorEnum getCode() {
        return this.apiError;
    }
}