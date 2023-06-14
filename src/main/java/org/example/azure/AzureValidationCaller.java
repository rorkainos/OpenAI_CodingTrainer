package org.example.azure;

import org.example.properties.AzureProperties;

public final class AzureValidationCaller extends AzureCaller {

    private final static String TRUE_FALSE_PROMPT = "Answer with one word small case true if it exists or false if it does not";
    private final static String LANGUAGE_VALIDATION_PROMPT = "Is %s a programming language?" + TRUE_FALSE_PROMPT;
    private final static String README_VALIDATION_PROMPT = "";// TODO Fill in
    private final static String TOPIC_EXPERIENCE_VALIDATION_PROMPT = "";
    public AzureValidationCaller(AzureProperties azureProperties) {
        super(azureProperties);
    }

    public boolean validateLanguage(final String language){
        final String prompt = LANGUAGE_VALIDATION_PROMPT.replaceFirst("%s", language);
        final String response = getChatCompletion(prompt).get(0);
        return isTrue(response);
    }

    public boolean validateChosenTopicExperience(final String language, final String topic, final String experience){
        //TODO you will need to use language, topic and experience in this query
        // final String prompt = TOPIC_EXPERIENCE_VALIDATION_PROMPT.replaceFirst("%s", experience);
        // final String response = getChatCompletion(prompt).get(0);
        // return isTrue(response);
        return false;
    }

    public boolean validateReadMe(final String readMe){
        final String prompt = README_VALIDATION_PROMPT.replaceFirst("%s", readMe);
        final String response = getChatCompletion(prompt).get(0);
        return isTrue(response);
    }

    private boolean isTrue(final String response){
        return Boolean.parseBoolean(response);
    }
}
