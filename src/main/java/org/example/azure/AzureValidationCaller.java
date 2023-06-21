package org.example.azure;

import org.example.properties.AzureProperties;

public final class AzureValidationCaller extends AzureCaller {

    private final static String TRUE_FALSE_PROMPT =
            "Answer with one word small case true if it exists or false if it does not";

    private final static String LANGUAGE_VALIDATION_PROMPT =
            "Is %s a programming language? " +
            TRUE_FALSE_PROMPT;

    private final static String README_VALIDATION_PROMPT =
            "Is it possible to get information on subject in quotation marks \"%s\"? and is this subject related to computer science?" +
            TRUE_FALSE_PROMPT;

    private final static String TOPIC_EXPERIENCE_VALIDATION_PROMPT =
            "Is this experience in quotation marks \"%s\" relevant to %s programming language and %s topic? " +
            TRUE_FALSE_PROMPT;

    private final static String TOPIC_VALIDATION_PROMPT =
            "Is this topic in quotation marks \"%s\" relevant to %s programming language? " +
                    TRUE_FALSE_PROMPT;


    public AzureValidationCaller(AzureProperties azureProperties) {
        super(azureProperties);
    }

    public boolean validateLanguage(final String language){
        final String prompt = LANGUAGE_VALIDATION_PROMPT.replaceFirst("%s", language);
        final String response = getChatCompletion(prompt).get(0);
        System.out.printf(
                "RESULT: %s, validateLanguage, language: %s\n",
                response,
                language);

        return isTrue(response);
    }

    public boolean validateTopic(final String topic, final String language){
        final String prompt = String.format(TOPIC_VALIDATION_PROMPT, topic, language);
        final String response = getChatCompletion(prompt).get(0);
        System.out.printf(
                "RESULT: %s, validateLanguage, topic: %s, language: %s\n",
                response,
                topic,
                language);

        return isTrue(response);
    }

    public boolean validateChosenTopicExperience(final String language, final String topic, final String experience){
        if(experience.toLowerCase().equals("none")){
            return true;
        }
        final String prompt = String.format(TOPIC_EXPERIENCE_VALIDATION_PROMPT, experience, language, topic);
        final String response = getChatCompletion(prompt).get(0);
        System.out.printf(
                "RESULT: %s, validateChosenTopicExperience, language: %s, topic: %s, experience: %s\n",
                response,
                language,
                topic,
                experience);

        return isTrue(response);
    }

    public boolean validateReadMe(final String readMe){
        String[] readMeTopics = readMe.split(",");
        String response = "false";

        if(readMe.contains("&") || readMe.contains(";")){
            return false;
        }
        if(readMe.toLowerCase().equals("no") ||
                readMe.toLowerCase().equals("none")){
            return true;
        }
        for (String topic : readMeTopics){
            final String prompt = String.format(README_VALIDATION_PROMPT, readMe);
            response = getChatCompletion(prompt).get(0);
            System.out.printf(
                    "RESULT: %s, validateReadMe, readMe topic: %s\n",
                    response,
                    topic);

        }
        return isTrue(response);
    }

    private boolean isTrue(final String response){
        return Boolean.parseBoolean(response);
    }
}
