package org.example.exceptions;

public class InvalidResponseException extends Exception {
    public InvalidResponseException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Response from API is invalid.";
    }
}
