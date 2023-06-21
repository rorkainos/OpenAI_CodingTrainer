package org.example.azure;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import org.example.properties.AzureProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AzureCaller {
    private AzureProperties azureProperties;

    public AzureCaller(AzureProperties azureProperties){
        this.azureProperties = azureProperties;
    }

    public List<String> getListOfTopics(final String language){

        final String prompt = String.format(
                "Provide me list without numbers of topics to learn in %s. Provide answer in the following format \"topic1; topic2; topic3;...\"",
                language);

        List<String> topics = Arrays.stream(
                getChatCompletion(prompt)
                        .get(0)
                        .split(";"))
                .toList();

        return topics;
    }

    protected List<String> getCompletion(List<String> prompts){

        List<String> result = new ArrayList<>();
        List<String> promptList = new ArrayList<>();
        promptList.addAll(prompts);

        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint(azureProperties.apiEndpoint())
                .credential(new AzureKeyCredential(azureProperties.apiKey()))
                .buildClient();

        Completions completions = client.getCompletions(azureProperties.apiDeployment(), new CompletionsOptions(promptList));

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

    protected List<String> getChatCompletion(String prompt) {
        List<String> result = new ArrayList<>();

        List<ChatMessage> chatMessages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatRole.USER);
        chatMessage.setContent(prompt);
        chatMessages.add(chatMessage);

        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint(azureProperties.apiEndpoint())
                .credential(new AzureKeyCredential(azureProperties.apiKey()))
                .buildClient();

        ChatCompletions completions = client.getChatCompletions(azureProperties.apiDeployment(), new ChatCompletionsOptions(chatMessages));

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
