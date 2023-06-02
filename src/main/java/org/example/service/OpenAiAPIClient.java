package org.example.service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import org.example.exceptions.InvalidPromptException;
import org.example.exceptions.PropertyNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenAiAPIClient {

    private static String apiKey;
    private static String azureEndpoint;
    private static String deploymentId;
    public OpenAiAPIClient() throws IOException, PropertyNotFoundException {
        apiKey = ReadPropertyFileService.getOpenAIAPIKey();
        azureEndpoint = ReadPropertyFileService.getOpenAIAPIEndpoint();
        deploymentId = ReadPropertyFileService.getOpenAIAPIDeployment();
    }

    public static List<String> getCompletion(List<String> prompts) throws InvalidPromptException {
        List<String> result = new ArrayList<>();
        if(prompts == null || prompts.isEmpty()) {
            throw new InvalidPromptException();
        }

        List<String> promptList = new ArrayList<>();
        promptList.addAll(prompts);

        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint(azureEndpoint)
                .credential(new AzureKeyCredential(apiKey))
                .buildClient();

        Completions completions = client.getCompletions(deploymentId, new CompletionsOptions(promptList));

        System.out.printf("Model ID=%s is created at %d.%n", completions.getId(), completions.getCreated());
        for (Choice choice : completions.getChoices()) {
            result.add(choice.getText());
            System.out.printf("Index: %d, Text: %s.%n", choice.getIndex(), choice.getText());
        }

        CompletionsUsage usage = completions.getUsage();
        System.out.printf("Usage: number of prompt token is %d, "
                        + "number of completion token is %d, and number of total tokens in request and response is %d.%n",
                usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());

        return result;
    }

    public static List<String> getChatCompletion(String prompt) throws InvalidPromptException {
        List<String> result = new ArrayList<>();
        if(prompt == null || prompt == "") {
            throw new InvalidPromptException();
        }

        List<ChatMessage> chatMessages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatRole.USER);
        chatMessage.setContent(prompt);
        chatMessages.add(chatMessage);

        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint(azureEndpoint)
                .credential(new AzureKeyCredential(apiKey))
                .buildClient();

        ChatCompletions completions = client.getChatCompletions(deploymentId, new ChatCompletionsOptions(chatMessages));

        System.out.printf("Model ID=%s is created at %d.%n", completions.getId(), completions.getCreated());
        for (ChatChoice choice : completions.getChoices()) {
            result.add(choice.getMessage().getContent());
            System.out.printf("Index: %d, Text: %s.%n", choice.getIndex(), String.valueOf(choice.getMessage()));
        }

        CompletionsUsage usage = completions.getUsage();
        System.out.printf("Usage: number of prompt token is %d, "
                        + "number of completion token is %d, and number of total tokens in request and response is %d.%n",
                usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());

        return result;
    }
}
