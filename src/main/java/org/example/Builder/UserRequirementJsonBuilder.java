package org.example.Builder;

import org.example.exceptions.InvalidPromptException;
import org.example.exceptions.PropertyNotFoundException;
import org.example.models.CodeBaseRequirements;
import org.example.models.LearningLevel;
import org.example.models.UserLearningRequirement;
import org.example.validators.AzureValidation;
import org.example.validators.CodeBaseRequirementsValidator;

import java.io.IOException;

/*
 * Class that validates and creates the json detailing the structure of what the
 * user wants from the generated program
 *
 * rorourke 06.06.2023
 */
public class UserRequirementJsonBuilder {

    private String language;
    private LearningLevel learningLevel;
    private String learningLevelContext;
    private String readMeTopics;
    private final CodeBaseRequirementsValidator codeBaseRequirementsValidator;
    private CodeBaseRequirements codeBaseRequirements;

    public UserRequirementJsonBuilder(CodeBaseRequirementsValidator codeBaseRequirementsValidator) {
        this.codeBaseRequirementsValidator = codeBaseRequirementsValidator;
    }

    public boolean setLanguage(String language) throws InvalidPromptException, PropertyNotFoundException, IOException {
        AzureValidation validation = codeBaseRequirementsValidator.validateLanguage(language);

        if(validation.isPass())
            this.language = language;

        System.out.println("Attribute: language | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        return validation.isPass();
    }

    public boolean setLearningLevel(final String learningLevelNumber) {
        AzureValidation validation = codeBaseRequirementsValidator.validateLearningLevel(learningLevelNumber);

        if(validation.isPass())
            this.learningLevel = LearningLevel.fromNumber(Integer.parseInt(learningLevelNumber));

        System.out.println("Attribute: Learning Level | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        return validation.isPass();
    }

    public boolean setLearningLevelContext(final String context) {
        AzureValidation validation = codeBaseRequirementsValidator.validateLearningLevelContext(language);

        if(validation.isPass())
            this.learningLevelContext = context;

        System.out.println("Attribute: Learning Level Context | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        return validation.isPass();
    }

    public boolean setReadMeTopics(String readMeTopics) {
        AzureValidation validation = codeBaseRequirementsValidator.validateReadMeTopics(readMeTopics);

        if (validation.isPass())
            this.readMeTopics = readMeTopics;

        System.out.println("Attribute: readMeTopics | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        return validation.isPass();
    }

    public CodeBaseRequirements build(){

        if(codeBaseRequirements != null)
            return this.codeBaseRequirements;

        if(readMeTopics == null && language == null && learningLevel == null && learningLevelContext == null)
            throw new IllegalStateException("Attributes to create Requirements for code base are not all met");

        System.out.println("Creating Code Base Requirements ..... ");
        this.codeBaseRequirements = new CodeBaseRequirements(language, new UserLearningRequirement(learningLevel, learningLevelContext), readMeTopics);
        return codeBaseRequirements;
    }
}
