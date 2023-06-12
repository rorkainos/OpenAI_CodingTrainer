package org.example.models;

/*
* The users input is to be collected and put into a validated JSON format.
* This class serves to define the Structure of the JSON
* This allows us to carefully craft a great request to Azure to build a good code base.
*
* rorourke
*/
public class CodeBaseRequirements {
    private String language;
    private UserLearningRequirement userLearningRequirement;
    private String readMeTopics;

    public CodeBaseRequirements(String language, UserLearningRequirement userLearningRequirement, String readMeTopics) {
        this.language = language;
        this.userLearningRequirement = userLearningRequirement;
        this.readMeTopics = readMeTopics;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public UserLearningRequirement getUserLearningRequirement() {
        return userLearningRequirement;
    }

    public void setUserLearningRequirement(UserLearningRequirement userLearningRequirement) {
        this.userLearningRequirement = userLearningRequirement;
    }

    public String getReadMeTopics() {
        return readMeTopics;
    }

    public void setReadMeTopics(String readMeTopics) {
        this.readMeTopics = readMeTopics;
    }
}
