package org.example.validators;

import org.example.exceptions.InvalidPromptException;
import org.example.exceptions.PropertyNotFoundException;
import org.example.models.UserLearningRequirement;
import org.example.service.OpenAiAPIClient;

import java.io.IOException;
import java.util.List;

/*
* to be implemented by connor
*/
public class CodeBaseRequirementsValidator {
    public CodeBaseRequirementsValidator() throws PropertyNotFoundException, IOException {
    }

    public AzureValidation validateReadMeTopics(final String readMeTopics){
        // node.js
        return new AzureValidation(true, "needs implemented");
    }

    public AzureValidation validateLanguage(final String language) throws InvalidPromptException, PropertyNotFoundException, IOException {
        // call azure endpoint to validate
        OpenAiAPIClient apiClient = new OpenAiAPIClient();

        List<String> result = apiClient.getChatCompletion(String.format("Is %s a programming language? " +
                "Answer with one word True or False", language));
        System.out.printf(result.get(0));
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
