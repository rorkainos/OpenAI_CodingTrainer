package org.example.validators;

import org.example.properties.AzureProperties;
import org.example.azure.AzureValidationCaller;

/*
* to be implemented by connor
*/
public class CodeBaseRequirementsValidator {
    private final AzureValidationCaller azureValidationCaller;

    public CodeBaseRequirementsValidator(AzureProperties azureProperties) {
        this.azureValidationCaller = new AzureValidationCaller(azureProperties);
    }

    public AzureValidation validateReadMeTopics(final String readMeTopics){
        // node.js
        return new AzureValidation(true, "needs implemented");
    }

    public AzureValidation validateLanguage(final String language){
        if(azureValidationCaller.validateLanguage(language))
            return new AzureValidation(true, "Acceptable Language Passed");
        else
            return new AzureValidation(false, "This is not an acceptable software language as of 2021");
    }

    public AzureValidation validateLanguageCurrentExperience(final String language, final String topic, final String context){
        // call azure endpoint to validate
        return new AzureValidation(true, "needs implemented");
    }
}
