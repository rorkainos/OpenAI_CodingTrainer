package org.example.models;

/*
*  This class bundles together the chosen Learning Level the user chooses along with
*  some context around it.
*
*  For example the user might choose Unit testing with an emphasis on certain design patterns.
*  rorourke
*/
public class UserLearningRequirement {
    private LearningLevel learningLevel;
    private String contextOfLearning;

    public UserLearningRequirement(LearningLevel learningLevel, String contextOfLearning) {
        this.learningLevel = learningLevel;
        this.contextOfLearning = contextOfLearning;
    }

    public LearningLevel getLearningLevel() {
        return learningLevel;
    }

    public void setLearningLevel(LearningLevel learningLevel) {
        this.learningLevel = learningLevel;
    }

    public String getContextOfLearning() {
        return contextOfLearning;
    }

    public void setContextOfLearning(String contextOfLearning) {
        this.contextOfLearning = contextOfLearning;
    }
}
