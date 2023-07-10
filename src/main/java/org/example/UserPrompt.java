package org.example;

import org.example.Builder.UserRequirementJsonBuilder;
import org.example.azure.AzureListOfTopics;
import org.example.models.CodeBaseRequirements;
import org.example.properties.AzureProperties;
import org.example.validators.CodeBaseRequirementsValidator;

import java.util.List;
import java.util.Scanner;

public class UserPrompt {

    private UserRequirementJsonBuilder jsonBuilder ;
    private AzureListOfTopics aiCaller;

    public UserPrompt(AzureProperties azureProperties) {
        this.aiCaller = new AzureListOfTopics(azureProperties);
        this.jsonBuilder = new UserRequirementJsonBuilder(new CodeBaseRequirementsValidator(azureProperties));
    }

    public CodeBaseRequirements build(){
        this.setChosenLanguage();
        this.setChosenTopic();
        this.setChosenReadMe();
        this.setComplexity();
        return this.jsonBuilder.build();
    }

    private void setChosenLanguage(){
        System.out.println("What Software Language would you like to learn today ");
        Scanner scanner = new Scanner(System.in);
        while (!jsonBuilder.setLanguage(scanner.nextLine())) {
            System.out.println("\n Please enter a valid language.... \n");
        }
    }

    private void setCustomTopic(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Please provide custom topic and hit enter. \n");
        while (!jsonBuilder.setLanguageTopic(scanner.nextLine(), true)) {
            System.out.println("\n Please enter a valid topic.... \n");
        }
    }

    private void setChosenTopic(){
        Scanner scanner = new Scanner(System.in);
        List<String> topics = aiCaller.getListOfTopics(jsonBuilder.getLanguage());
        for(int i = 0; i< topics.size(); i++)
            System.out.println(i + 1 + " : " + topics.get(i));
        System.out.println(topics.size() + 1 + " :  Custom topic.");
        System.out.println("From the list above select what topic you would like to upskill in. Please select a number");

        int topicElement = Integer.parseInt(scanner.nextLine()) - 1;
        if (topicElement==topics.size()){
            setCustomTopic();
        }
        else {
            jsonBuilder.setLanguageTopic(topics.get(topicElement), false);
        }
    }

    private void setChosenTopicExperience(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What current experience do you have with this topic. If none please say");

        while (!jsonBuilder.setLanguageTopicCurrentExperience(scanner.nextLine())) {
            System.out.println("\n Please enter a valid current experience \n");
        }
    }

    private void setChosenReadMe(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Finally would you like any helpful ReadMe documentation, type no if not");
        System.out.println("If so please specify topics to cover.");
        System.out.println("Each topic should be separated by a comma");

        while (!jsonBuilder.setReadMeTopics(scanner.nextLine())) {
            System.out.println("\n Please enter valid topics or type no to escape  \n");
        }
    }

    private void setComplexity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How difficult do you want the code to be?");
        System.out.println("Choose on a scale from 0 to 5, where 0 is very easy and 5 is very difficult.");

        while (!jsonBuilder.setComplexity(Integer.parseInt(scanner.nextLine()))) {
            System.out.println("\n Please enter a valid complexity \n");
        }
    }
}
