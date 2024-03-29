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
        if(azureValidationCaller.validateReadMe(readMeTopics))
            return new AzureValidation(true, "Acceptable ReadMe topic(s) Passed");
        else
            return new AzureValidation(false, "This is/are not acceptable ReadMe topic(s)");
    }

    public AzureValidation validateLanguage(final String language){
        if(azureValidationCaller.validateLanguage(language))
            return new AzureValidation(true, "Acceptable Language Passed");
        else
            return new AzureValidation(false, "This is not an acceptable software language as of 2021");
    }

    public AzureValidation validateTopic(final String topic, final String language, boolean customTopic){
        if(customTopic) {
            if (azureValidationCaller.validateTopic(topic, language))
                return new AzureValidation(true, "Custom topic selected successfully.");
            else
                return new AzureValidation(false, "This is not an acceptable topic, please select number from the list.");
        }
        else{
            return new AzureValidation(true, "Topic selected from the list.");
        }
    }

    public AzureValidation validateLanguageCurrentExperience(final String language, final String topic, final String context){
        // call azure endpoint to validate
        if(azureValidationCaller.validateChosenTopicExperience(language, topic, context))
            return new AzureValidation(true, "Acceptable Experience Level for selected language and topic Passed");
        else
            return new AzureValidation(false, "This is not an acceptable experience level as of 2021");
    }

    public AzureValidation validateComplexity(int complexity) {
        if(complexity >= 0 && complexity <= 5)
            return new AzureValidation(true, "Acceptable complexity level.");
        else
            return new AzureValidation(false, "This is not an acceptable complexity level.");
    }
}
