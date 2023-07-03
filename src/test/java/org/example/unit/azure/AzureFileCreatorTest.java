package org.example.unit.azure;

import org.example.azure.AzureFileCreator;
import org.example.models.CodeBaseRequirements;
import org.example.properties.AzureProperties;
import org.example.properties.PropertiesExtractor;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AzureFileCreatorTest {

    @Test
    public void filterResponseByRegex() {
        String text = "||This|| is | a | ||piece|| of text that uses multiple $$symbols$$. ||Now with multiple|| words as well.";
        String[] resultArray = {"This", "piece", "Now with multiple"};
        List<String> expectedResult = new ArrayList<>(Arrays.asList(resultArray));
        String regex = "\\|\\|(.+?)\\|\\|";
        try {
            AzureProperties properties = PropertiesExtractor.getAzureProperties();
            AzureFileCreator azureFileCreator = new AzureFileCreator(properties);
            List<String> result = azureFileCreator.filterResponseByRegex(text, regex);
            assertEquals(expectedResult, result);
        }
        catch (Exception e) {
            fail();
        }
    }
}