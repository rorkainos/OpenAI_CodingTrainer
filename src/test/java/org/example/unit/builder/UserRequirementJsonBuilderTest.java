package org.example.unit.builder;

import org.example.Builder.UserRequirementJsonBuilder;
import org.example.models.CodeBaseRequirements;
import org.example.validators.AzureValidation;
import org.example.validators.CodeBaseRequirementsValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserRequirementJsonBuilderTest {

    private UserRequirementJsonBuilder userRequirementJsonBuilder;

    @Mock
    private CodeBaseRequirementsValidator codeBaseRequirementsValidator;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userRequirementJsonBuilder = new UserRequirementJsonBuilder(codeBaseRequirementsValidator);
    }

    @Test(expected = IllegalStateException.class)
    public void testBuildFailed(){
        userRequirementJsonBuilder.build();
    }

    @Test
    public void testSetReadMeFailed(){
        when(codeBaseRequirementsValidator.validateReadMeTopics(any())).thenReturn(new AzureValidation(false, "testing failed validation"));
        assertFalse(userRequirementJsonBuilder.setReadMeTopics("test"));
    }

    @Test
    public void testSetReadMePassed(){
        when(codeBaseRequirementsValidator.validateReadMeTopics(any())).thenReturn(new AzureValidation(true, "testing validation"));
        assertTrue(userRequirementJsonBuilder.setReadMeTopics("test"));
    }

    @Test
    public void testSetLanguageFailed(){
        when(codeBaseRequirementsValidator.validateLanguage(any())).thenReturn(new AzureValidation(false, "testing failed validation"));
        assertFalse(userRequirementJsonBuilder.setLanguage("test"));
    }

    @Test
    public void testSetLanguagePassed(){
        when(codeBaseRequirementsValidator.validateLanguage(any())).thenReturn(new AzureValidation(true, "testing validation"));
        assertTrue(userRequirementJsonBuilder.setLanguage("test"));
    }

    @Test
    public void testSetLanguageTopicFailed(){
        when(codeBaseRequirementsValidator.validateLanguageCurrentExperience(any(), any(), any())).thenReturn(new AzureValidation(false, "testing validation"));
        assertFalse(userRequirementJsonBuilder.setLanguageTopicCurrentExperience("test"));
    }

    @Test
    public void testSetLanguageTopicExperiencePassed(){
        when(codeBaseRequirementsValidator.validateLanguageCurrentExperience(any(), any(), any())).thenReturn(new AzureValidation(true, "testing validation"));
        assertTrue(userRequirementJsonBuilder.setLanguageTopicCurrentExperience("test"));
    }

    @Test
    public void testSetDifficultyPassed() {
        when(codeBaseRequirementsValidator.validateDifficulty(2)).thenReturn(new AzureValidation(true, "testing validation"));
        assertTrue(userRequirementJsonBuilder.setDifficulty(2));
    }

    @Test
    public void testSetDifficultyFailed() {
        when(codeBaseRequirementsValidator.validateDifficulty(9)).thenReturn(new AzureValidation(false, "testing validation"));
        assertFalse(userRequirementJsonBuilder.setDifficulty(9));
    }

    @Test
    public void testBuildSucceeded(){
        final String language = "node.js";
        final String readMe = "readMe";
        final String topic = "topic";
        final String topicExperience = "experience";
        final int difficulty = 2;

        when(codeBaseRequirementsValidator.validateLanguage(any())).thenReturn(new AzureValidation(true, "testing validation"));
        when(codeBaseRequirementsValidator.validateReadMeTopics(any())).thenReturn(new AzureValidation(true, "testing validation"));
        when(codeBaseRequirementsValidator.validateLanguageCurrentExperience(any(), any(), any())).thenReturn(new AzureValidation(true, "testing validation"));

        userRequirementJsonBuilder.setLanguage(language);
        userRequirementJsonBuilder.setReadMeTopics(readMe);
        userRequirementJsonBuilder.setLanguageTopic(topic);
        userRequirementJsonBuilder.setLanguageTopicCurrentExperience(topicExperience);
        userRequirementJsonBuilder.setDifficulty(2);

        CodeBaseRequirements codeBaseRequirements = userRequirementJsonBuilder.build();
        assertNotNull(codeBaseRequirements);
        assertEquals(language, codeBaseRequirements.language());
        assertEquals(readMe, codeBaseRequirements.readMeTopics());
        assertEquals(topic, codeBaseRequirements.topic());
        assertEquals(topicExperience, codeBaseRequirements.currentExperience());
        assertEquals(difficulty, codeBaseRequirements.difficulty());
    }
}
