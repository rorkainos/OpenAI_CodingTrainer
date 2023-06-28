package org.example.Builder;

import org.example.models.CodeBaseRequirements;
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
    private String currentExperience;
    private String languageTopic;
    private String readMeTopics;
    private final CodeBaseRequirementsValidator codeBaseRequirementsValidator;

    public UserRequirementJsonBuilder(CodeBaseRequirementsValidator codeBaseRequirementsValidator) {
        this.codeBaseRequirementsValidator = codeBaseRequirementsValidator;
    }

    public CodeBaseRequirements build() {
        if(readMeTopics == null && language == null && currentExperience == null && languageTopic == null)
            throw new IllegalStateException("Attributes to create Requirements for code base are not all met");

        System.out.println("Creating Code Base Requirements ..... ");
        return new CodeBaseRequirements(language, languageTopic, currentExperience, readMeTopics);
    }

    public String getLanguage() {
        return language;
    }

    public boolean setLanguage(final String language) {
        AzureValidation validation = codeBaseRequirementsValidator.validateLanguage(language);

        if(validation.isPass())
            this.language = language;

        System.out.println("Attribute: language | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        return validation.isPass();
    }

    public boolean setLanguageTopic(final String topic, boolean customTopic) {

        AzureValidation validation = codeBaseRequirementsValidator.validateTopic(topic, this.language, customTopic);

        if(validation.isPass())
            this.languageTopic = topic;

        System.out.println("Attribute: languageTopic, language | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        System.out.println("Topic Selected : " + languageTopic);
        return validation.isPass();
    }

    public boolean setLanguageTopicCurrentExperience(final String currentExperience) {
        AzureValidation validation = codeBaseRequirementsValidator.validateLanguageCurrentExperience(language, languageTopic, currentExperience);

        if(validation.isPass())
            this.currentExperience = currentExperience;

        System.out.println("Attribute: Learning Level Context | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        return validation.isPass();
    }

    public boolean setReadMeTopics(final String readMeTopics) {
        AzureValidation validation = codeBaseRequirementsValidator.validateReadMeTopics(readMeTopics);

        if (validation.isPass())
            this.readMeTopics = readMeTopics;

        System.out.println("Attribute: readMeTopics | Validation Result | " + validation.isPass() + " Reason: " + validation.reason());
        return validation.isPass();
    }
}
