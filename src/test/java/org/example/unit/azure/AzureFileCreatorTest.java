package org.example.unit.azure;

import org.example.azure.AzureCaller;
import org.example.azure.AzureFileCreator;
import org.example.exceptions.PropertyNotFoundException;
import org.example.properties.AzureProperties;
import org.example.properties.PropertiesExtractor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import static org.mockito.Mockito.when;

public class AzureFileCreatorTest {

    @Mock
    private AzureFileCreator azureFileCreator;
    private AzureProperties azureProperties;
    @Before
    public void setUp() throws PropertyNotFoundException, IOException {
        azureProperties = new AzureProperties(PropertiesExtractor.getAzureProperties().apiKey(),
                PropertiesExtractor.getAzureProperties().apiEndpoint(),
                PropertiesExtractor.getAzureProperties().apiDeployment());
        MockitoAnnotations.initMocks(this);
        azureFileCreator = new AzureFileCreator(azureProperties);
    }

    @Test
    public void getProgramDetails() {
    }

    @Test
    public void filterResponseByRegex() {

    }
}