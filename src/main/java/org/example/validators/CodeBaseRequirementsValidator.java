package org.example.validators;

/*
* to be implemented by connor
*/
public class CodeBaseRequirementsValidator {

    public AzureValidation validateReadMeTopics(final String readMeTopics){
        // node.js

        return new AzureValidation(true, "needs implemented");
    }

    public AzureValidation validateLanguage(final String language){
        return new AzureValidation(true, "needs implemented");
    }

    public AzureValidation validateLanguageCurrentExperience(final String language, final String topic, final String context){
        // call azure endpoint to validate
        return new AzureValidation(true, "needs implemented");
    }
}
