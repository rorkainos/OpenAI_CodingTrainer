package org.example.exceptions;

public class InvalidPromptException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid prompt provided.";
    }
}
