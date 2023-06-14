package org.example;

import org.example.exceptions.PropertyNotFoundException;
import org.example.models.CodeBaseRequirements;
import org.example.properties.AzureProperties;
import org.example.properties.PropertiesExtractor;

import java.io.IOException;

public class App 
{
    public static void main( String[] args )
    {
        AzureProperties azureProperties = getProperties();

        if(azureProperties == null)
            return;

        UserPrompt userPrompts = new UserPrompt(azureProperties);
        CodeBaseRequirements userRequirements = userPrompts.build();
    }

    private static AzureProperties getProperties(){
        try{
            return PropertiesExtractor.getAzureProperties();
        } catch (PropertyNotFoundException e) {
            System.out.println("cannot read from properties.properties file");
            return null;
        } catch (IOException e) {
            System.out.println("other file related error occurred. Make sure you have the correct properties.properties file");
            return null;
        }
    }
}
