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
    private String language;
    private final String promptDetals = "Answer with one word small case true if it exists or false if it does not.";
    private final OpenAiAPIClient apiClient;
    public CodeBaseRequirementsValidator() throws PropertyNotFoundException, IOException {
        this.apiClient = new OpenAiAPIClient();
    }

    public AzureValidation validateReadMeTopics(final String readMeTopics) throws InvalidPromptException {

        List<String> result = apiClient.getChatCompletion(
                String.format("Is it possible to get information on %s regarding %s programming language? %s",
                readMeTopics,
                this.language,
                this.promptDetals));

        boolean res = Boolean.valueOf(result.get(0));

        return new AzureValidation(res, "Checks if it is possible to tackle topics using selected language.");
    }

    public AzureValidation validateLanguage(final String language) throws InvalidPromptException {
        this.language = language;
        List<String> result = apiClient.getChatCompletion(
                String.format("Is %s a programming language? %s",
                language,
                this.promptDetals));

        boolean res = Boolean.valueOf(result.get(0));

        return new AzureValidation(res, "Check if language exists.");
    }

    public AzureValidation validateLearningLevelContext(final String context){


        return new AzureValidation(true, "needs implemented");
    }

    public AzureValidation validateLearningLevel(final String learningLevelNumber) throws InvalidPromptException {
        // call azure endpoint to validate
        List<String> result = apiClient.getChatCompletion(
                String.format("Is it possible to %s using %s programming language? %s",
                learningLevelNumber,
                this.language,
                this.promptDetals));

        boolean res = Boolean.valueOf(result.get(0));

        return new AzureValidation(res, "Checks if topic is possible to be managed using selected language");
    }
}
