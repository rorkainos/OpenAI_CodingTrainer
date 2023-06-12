package org.example;

import org.example.Builder.CodeBaseBuilder;
import org.example.Builder.UserRequirementJsonBuilder;
import org.example.validators.CodeBaseRequirementsValidator;

import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        AzureCaller aiCaller = new AzureCaller();
        UserRequirementJsonBuilder jsonBuilder = new UserRequirementJsonBuilder(new CodeBaseRequirementsValidator());

        // ---- language ---------
        System.out.println("What Software Language would you like to learn today ");
        Scanner scanner = new Scanner(System.in);
        while (!jsonBuilder.setLanguage(scanner.nextLine())) {
            System.out.println("\n Please enter a valid language.... \n");
        }

        // ---- topic ---------
        List<String> topics = aiCaller.getListOfTopics(jsonBuilder.getLanguage());
        for(int i = 0; i< topics.size(); i++)
            System.out.println(i + " : " + topics.get(i));

        System.out.println("From the list below select what topic you would like to upskill in. Please select a number");
        int topicElement = Integer.parseInt(scanner.nextLine());
        jsonBuilder.setLanguageTopic(topics.get(topicElement));

        // ---- experience with topic ---------
        System.out.println("What current experience do you have with this topic. If none please say");

        while (!jsonBuilder.setLanguageTopicCurrentExperience(scanner.nextLine())) {
            System.out.println("\n Please enter a valid current experience \n");
        }

        // ---- read me topics ---------
        System.out.println("Finally would you like any helpful ReadMe documentation, type no if not");
        System.out.println("If so please specify topics to cover.");
        System.out.println("Each topic should be separated by a comma");

        while (!jsonBuilder.setReadMeTopics(scanner.nextLine())) {
            System.out.println("\n Please enter valid topics or type no to escape  \n");
        }

        // generate code base using codeBaseRequirements
        CodeBaseBuilder programBuilder = new CodeBaseBuilder(jsonBuilder.build(), aiCaller);
        programBuilder.build();
    }
}
