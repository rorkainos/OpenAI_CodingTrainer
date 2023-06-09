package org.example.validators;

import org.example.models.UserLearningRequirement;

/*
* to be implemented by connor
*/
public class CodeBaseRequirementsValidator {

    public AzureValidation validateReadMeTopics(final String readMeTopics){
        // node.js

        return new AzureValidation(false, "needs implemented");
    }

    public AzureValidation validateLanguage(final String language){
        // call azure endpoint to validate
        return new AzureValidation(false, "needs implemented");
    }

    public AzureValidation validateUserLearningRequirement(final UserLearningRequirement userLearningRequirement){
        // call azure endpoint to validate
        return new AzureValidation(false, "needs implemented");
    }
}
