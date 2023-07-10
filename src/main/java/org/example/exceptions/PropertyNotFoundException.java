package org.example.exceptions;

public class PropertyNotFoundException extends Exception {
    private final String property;

    public PropertyNotFoundException(String property) {
        super();
        this.property = property;
    }

    @Override
    public String getMessage() {
        return "Property " + property + " not found.";
    }
}

