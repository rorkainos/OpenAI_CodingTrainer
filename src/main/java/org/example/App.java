package org.example;

import org.example.Builder.UserRequirementJsonBuilder;
import org.example.models.CodeBaseRequirements;
import org.example.models.LearningLevel;
import org.example.validators.CodeBaseRequirementsValidator;

import java.util.Scanner;

/**
 * A simple command line application that takes user input as an argument
 * and outputs it to the console
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserRequirementJsonBuilder jsonBuilder = new UserRequirementJsonBuilder(new CodeBaseRequirementsValidator());
        System.out.println("Description of Program... ");
        System.out.println("What Software Language would you like to learn today ");

        Scanner scanner = new Scanner(System.in);
        while (!jsonBuilder.setLanguage(scanner.nextLine())) {
            System.out.println("\n Please enter a valid language.... \n");
        }

        System.out.println("From the list below select what learning level you would like proceed with.");
        System.out.println("( Context surrounding this level with be asked for after )");

        for (LearningLevel level : LearningLevel.values()) {
            System.out.println(level);
        }

        while (!jsonBuilder.setLearningLevel(scanner.nextLine())) {
            System.out.println("\n Please enter a valid number.... \n");
        }

        System.out.println("Please select the context around the learning level you have chosen.");
        System.out.println("This will be validated to see if it is achievable");

        while (!jsonBuilder.setLearningLevelContext(scanner.nextLine())) {
            System.out.println("\n Please enter a valid context.... \n");
        }

        System.out.println("Finally would you like any helpful ReadMe documentation, type no if not");
        System.out.println("If so please specify topics to cover.");
        System.out.println("Each topic should be separated by a comma");

        while (!jsonBuilder.setReadMeTopics(scanner.nextLine())) {
            System.out.println("\n Please enter valid topics or type no to escape  \n");
        }

        System.out.println("Thank you for specifying your requirements please wait while we build your code base ");
        CodeBaseRequirements codeBaseRequirements = jsonBuilder.build();

        // generate code base using codeBaseRequirements
    }
}
