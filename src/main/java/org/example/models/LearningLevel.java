package org.example.models;

/*
*  This enum class defines the various levels that our program will aim to generate for the code base.
*  Predefining these here ensures that the program doesn't generate anything while, and acts as guard rails in
*  some respects.
*
*  rorourke
*/
public enum LearningLevel {
    DEPENDENCY_MANAGEMENT("Learn How To Install And Manage Dependencies", 1),
    UNIT("Learn Unit Testing", 2),
    INTEGRATION("Learn Integration Testing", 3),
    CLOUD("Learn Cloud Integration", 4),
    SECURITY("Learn Basic Security Practices", 5),
    AI("Learn AI Integration", 6);

    private final String value;
    private final int number;

    private LearningLevel(String value, int number) {
        this.value = value;
        this.number = number;
    }

    public String getValue() {
        return value;
    }
    public int getNumber(){return number;}

    @Override
    public String toString() {
        return "Number: " + number + "\nValue: " + value + "\n";
    }

    public static LearningLevel fromNumber(int number) {
        for (LearningLevel level : LearningLevel.values()) {
            if (level.getNumber() == number) {
                return level;
            }
        }
        return null; // or throw an exception if desired
    }
}
