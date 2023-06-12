package org.example.validators;

import org.example.models.UserLearningRequirement;

/*
* to be implemented by connor
*/
public class CodeBaseRequirementsValidator {

    public AzureValidation validateReadMeTopics(final String readMeTopics){
        // node.js

        return new AzureValidation(true, "needs implemented");
    }

    public AzureValidation validateLanguage(final String language){
        // call azure endpoint to validate
        return new AzureValidation(true, "needs implemented");
    }

    public AzureValidation validateLearningLevelContext(final String context){
        // call azure endpoint to validate
        return new AzureValidation(true, "needs implemented");
    }

    public AzureValidation validateLearningLevel(final String learningLevelNumber){
        // call azure endpoint to validate
        return new AzureValidation(true, "needs implemented");
    }
}
