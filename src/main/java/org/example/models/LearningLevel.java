package org.example.models;

/*
*  This enum class defines the various levels that our program will aim to generate for the code base.
*  Predefining these here ensures that the program doesn't generate anything while, and acts as guard rails in
*  some respects.
*
*  rorourke
*/
public enum LearningLevel {
    DEPENDENCY_MANAGEMENT("Learn How To Install And Manage Dependencies"),
    UNIT("Learn Unit Testing"),
    INTEGRATION("Learn Integration Testing"),
    CLOUD("Learn Cloud Integration"),
    SECURITY("Learn Basic Security Practices"),
    AI("Learn AI Integration");

    private final String value;

    private LearningLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
