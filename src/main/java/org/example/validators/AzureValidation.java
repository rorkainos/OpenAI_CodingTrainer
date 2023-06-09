package org.example.validators;

public record AzureValidation(boolean isPass, String reason) {

    @Override
    public String reason() {
        if (isPass())
            return "Valid Requirement Passed";

        return reason;
    }
}
