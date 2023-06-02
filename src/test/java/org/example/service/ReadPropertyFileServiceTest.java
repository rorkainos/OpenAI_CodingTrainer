package org.example.service;

import org.example.exceptions.PropertyNotFoundException;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReadPropertyFileServiceTest {

    @Test
    public void testGetOpenAIAPIKeyShouldBeSuccessful() throws IOException, PropertyNotFoundException {
        final ReadPropertyFileService readPropertyFileService = new ReadPropertyFileService();
        final String apiKey = readPropertyFileService.getOpenAIAPIKey();

        assertNotNull(apiKey);
    }

    @Test
    public void testGetValueFromPropertiesFileReturnsPropertySuccessful() throws IOException, PropertyNotFoundException {
        final ReadPropertyFileService readPropertyFileService = new ReadPropertyFileService();
        final String property = "apiKey";
        final String result = readPropertyFileService.getValueFromPropertiesFile(property);

        assertNotNull(result);
    }
}