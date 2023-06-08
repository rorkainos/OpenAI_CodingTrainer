package org.example.Builder;

import org.example.models.CodeBaseRequirements;
import org.example.models.UserLearningRequirement;
import org.example.validators.AzureValidation;
import org.example.validators.CodeBaseRequirementsValidator;

/*
 * Class that validates and creates the json detailing the structure of what the
 * user wants from the generated program
 *
 * rorourke 06.06.2023
 */
public class UserRequirementJsonBuilder {

    private String language;
    private UserLearningRequirement userLearningRequirement;
    private String readMeTopics;
    private final CodeBaseRequirementsValidator codeBaseRequirementsValidator;
    private CodeBaseRequirements codeBaseRequirements;

    public UserRequirementJsonBuilder(CodeBaseRequirementsValidator codeBaseRequirementsValidator) {
        this.codeBaseRequirementsValidator = codeBaseRequirementsValidator;
    }

    public boolean setLanguage(String language) {
        AzureValidation validation = codeBaseRequirementsValidator.validateLanguage(language);

        if(validation.isPass())
            this.language = language;

        System.out.println("Attribute: language | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        return validation.isPass();
    }

    public boolean setUserLearningRequirement(UserLearningRequirement userLearningRequirement) {
        AzureValidation validation = codeBaseRequirementsValidator.validateUserLearningRequirement(userLearningRequirement);

        if(validation.isPass())
            this.userLearningRequirement = userLearningRequirement;

        System.out.println("Attribute: userLearningRequirement | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
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

        if(readMeTopics == null && language == null && userLearningRequirement == null)
            throw new IllegalStateException("Attributes to create Requirements for code base are not all met");

        System.out.println("Creating Code Base Requirements ..... ");
        this.codeBaseRequirements = new CodeBaseRequirements(language, userLearningRequirement, readMeTopics);
        return codeBaseRequirements;
    }
}
