package org.example.properties;

import org.example.exceptions.PropertyNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class PropertiesExtractor {

    public static AzureProperties azureProperties;

    private static final String API_KEY_VARIABLE = "apiKey";
    private static final String ENDPOINT_VARIABLE = "endpoint";
    private static final String DEPLOYMENT_VARIABLE = "deploymentName";
    private static final String PROPERTY_FILE_PATH = "properties.properties";

    public static AzureProperties getAzureProperties() throws PropertyNotFoundException, IOException {
        if (azureProperties != null)
            return azureProperties;

        azureProperties = getValueFromPropertiesFile();
        return azureProperties;
    }

    private static AzureProperties getValueFromPropertiesFile() throws IOException, PropertyNotFoundException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(PROPERTY_FILE_PATH)));

        final String apiKey = getSpecificPropertyFromFile(properties, API_KEY_VARIABLE);
        final String endpoint = getSpecificPropertyFromFile(properties, ENDPOINT_VARIABLE);
        final String deployment = getSpecificPropertyFromFile(properties, DEPLOYMENT_VARIABLE);
        return new AzureProperties(apiKey, endpoint, deployment);
    }

    private static String getSpecificPropertyFromFile(final Properties properties, final String property) throws PropertyNotFoundException {
        String data  = properties.getProperty(property);

        if(data == null) {
            throw new PropertyNotFoundException(property);
        }

        return data;
    }
}
