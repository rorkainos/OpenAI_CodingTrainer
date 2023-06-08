package org.example.Builder;

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
