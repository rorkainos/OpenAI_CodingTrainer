package org.example.azure;

import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import org.example.properties.AzureProperties;

import java.util.ArrayList;
import java.util.List;

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

    private final static String SYSTEM_PROMPT = "Assistant is an intelligent chatbot designed to help users create programming training materials";
    private static ChatMessage chatMessage = new ChatMessage(ChatRole.SYSTEM);
    private static List<ChatMessage> chatMessages = new ArrayList<>();
    public AzureValidationCaller(AzureProperties azureProperties) {
        super(azureProperties);
    }

    public boolean validateLanguage(final String language){
        final String prompt = LANGUAGE_VALIDATION_PROMPT.replaceFirst("%s", language);
        chatMessage.setContent(SYSTEM_PROMPT);
        chatMessages.add(chatMessage);

        final String response = getChatCompletion(chatMessages, prompt).get(0);

        System.out.printf(
                "RESULT: %s, validateLanguage, language: %s\n",
                response,
                language);

        return isTrue(response);
    }

    public boolean validateChosenTopicExperience(final String language, final String topic, final String experience){
        if(experience.equalsIgnoreCase("none")){
            return true;
        }
        final String prompt = String.format(TOPIC_EXPERIENCE_VALIDATION_PROMPT, experience, language, topic);
        chatMessage.setContent(SYSTEM_PROMPT);
        chatMessages.add(chatMessage);
        final String response = getChatCompletion(chatMessages, prompt).get(0);
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
        if(readMe.equalsIgnoreCase("no") ||
                readMe.equalsIgnoreCase("none")){
            return true;
        }
        for (String topic : readMeTopics){
            final String prompt = String.format(README_VALIDATION_PROMPT, readMe);
            chatMessage.setContent(SYSTEM_PROMPT);
            chatMessages.add(chatMessage);
            response = getChatCompletion(chatMessages, prompt).get(0);
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
