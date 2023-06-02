package org.example.service;

import org.example.exceptions.PropertyNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadPropertyFileService {
    private static final String API_KEY_VARIABLE = "apiKey";
    private static final String ENDPOINT_VARIABLE = "endpoint";
    private static final String DEPLOYMENT_VARIABLE = "deploymentName";
    private static final String PROPERTY_FILE_PATH = "src/main/properties/.properties";

    static String getValueFromPropertiesFile(final String property) throws IOException, PropertyNotFoundException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(PROPERTY_FILE_PATH)));

        String data  = properties.getProperty(property);
        if(data == null) {
            throw new PropertyNotFoundException(property);
        }
        return data;
    }

    public static String getOpenAIAPIKey() throws IOException, PropertyNotFoundException {
        return getValueFromPropertiesFile(API_KEY_VARIABLE);
    }
    public static String getOpenAIAPIEndpoint() throws IOException, PropertyNotFoundException {
        return getValueFromPropertiesFile(ENDPOINT_VARIABLE);
    }
    public static String getOpenAIAPIDeployment() throws IOException, PropertyNotFoundException {
        return getValueFromPropertiesFile(DEPLOYMENT_VARIABLE);
    }
}
