package org.example.azure;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import org.example.properties.AzureProperties;

import java.util.ArrayList;
import java.util.List;

public abstract class AzureCaller {
    private AzureProperties azureProperties;

    public AzureCaller(AzureProperties azureProperties){
        this.azureProperties = azureProperties;
    }

    public List<String> getListOfTopics(final String language){
        return List.of("Unit testing", "Integration testing");
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

        for (Choice choice : completions.getChoices()) {
            result.add(choice.getText());
        }

        return result;
    }

    protected List<String> getChatCompletion(List<ChatMessage> contextChatMessages, String prompt) {
        List<String> result = new ArrayList<>();

        List<ChatMessage> chatMessages = new ArrayList<>();
        if(!contextChatMessages.isEmpty()) {
            chatMessages.addAll(contextChatMessages);
        }
        ChatMessage chatMessage = new ChatMessage(ChatRole.USER);
        chatMessage.setContent(prompt);
        chatMessages.add(chatMessage);

        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint(azureProperties.apiEndpoint())
                .credential(new AzureKeyCredential(azureProperties.apiKey()))
                .buildClient();

        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setTemperature(0.0);

        ChatCompletions completions = client.getChatCompletions(azureProperties.apiDeployment(), chatCompletionsOptions);

        for (ChatChoice choice : completions.getChoices()) {
            result.add(choice.getMessage().getContent());
        }

        return result;
    }
}
