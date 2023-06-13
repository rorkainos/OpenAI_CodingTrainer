package org.example.unit.service;

import org.example.exceptions.InvalidPromptException;
import org.example.exceptions.PropertyNotFoundException;
import org.example.service.OpenAiAPIClient;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OpenAiAPIClientTest {

    @Test
    public void testGetCompletionShouldBeSuccessful() throws IOException, InvalidPromptException, PropertyNotFoundException {
        final OpenAiAPIClient apiClient = new OpenAiAPIClient();
        final List<String> prompts = new ArrayList<>();
        final String prompt = "What is 2 + 2?";
        prompts.add(prompt);
        final List<String> requestResponse = apiClient.getCompletion(prompts);

        assertNotNull(requestResponse);
    }

    @Test
    public void testSendRequestToAPIShouldFailWithInvalidPromptException() throws IOException, PropertyNotFoundException {
        final OpenAiAPIClient apiClient = new OpenAiAPIClient();
        final List<String> prompts = null;

        assertThrows(InvalidPromptException.class, () -> { apiClient.getCompletion(prompts); });
    }

    @Test
    public void testGetChatCompletionShouldBeSuccessful() throws IOException, InvalidPromptException, PropertyNotFoundException {
        final OpenAiAPIClient apiClient = new OpenAiAPIClient();
        final String prompt = "What is 2 + 2?";
        final List<String> requestResponse = apiClient.getChatCompletion(prompt);

        assertNotNull(requestResponse);
    }

    @Test
    public void testGetChatCompletionShouldFailWithInvalidPromptException() throws IOException, PropertyNotFoundException {
        final OpenAiAPIClient apiClient = new OpenAiAPIClient();
        final String prompt = null;

        assertThrows(InvalidPromptException.class, () -> { apiClient.getChatCompletion(prompt); });
    }
}