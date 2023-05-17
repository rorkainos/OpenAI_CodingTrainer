package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class ReadPropertyFile {
    private static final String BASE_PATH_VARIABLE = "basePath";
    private static final String PROPERTY_FILE_NAME = "properties.properties";

    private static String getValueFromPropertiesFile(final String property) throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(PROPERTY_FILE_NAME)));

        // Read properties
        return properties.getProperty(property);
    }

    public static String getProjectBasePath() throws IOException {
        return getValueFromPropertiesFile(BASE_PATH_VARIABLE);
    }
}
