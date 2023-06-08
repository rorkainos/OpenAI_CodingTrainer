package org.example.unit.builder;

import org.example.Builder.UserRequirementJsonBuilder;
import org.example.models.CodeBaseRequirements;
import org.example.models.LearningLevel;
import org.example.models.UserLearningRequirement;
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
    public void testSetUserLearningRequirementFailed(){
        when(codeBaseRequirementsValidator.validateUserLearningRequirement(any())).thenReturn(new AzureValidation(false, "testing failed validation"));
        assertFalse(userRequirementJsonBuilder.setUserLearningRequirement(new UserLearningRequirement(LearningLevel.AI, "test context")));
    }

    @Test
    public void testSetUserLearningRequirementPassed(){
        when(codeBaseRequirementsValidator.validateUserLearningRequirement(any())).thenReturn(new AzureValidation(true, "testing validation"));
        assertTrue(userRequirementJsonBuilder.setUserLearningRequirement(new UserLearningRequirement(LearningLevel.AI, "test context")));
    }

    @Test
    public void testBuildSucceeded(){
        final String language = "node.js";
        final String readMe = "readMe";
        final UserLearningRequirement userLearningRequirement = new UserLearningRequirement(LearningLevel.AI, "test context");

        when(codeBaseRequirementsValidator.validateLanguage(any())).thenReturn(new AzureValidation(true, "testing validation"));
        when(codeBaseRequirementsValidator.validateReadMeTopics(any())).thenReturn(new AzureValidation(true, "testing validation"));
        when(codeBaseRequirementsValidator.validateUserLearningRequirement(any())).thenReturn(new AzureValidation(true, "testing validation"));

        userRequirementJsonBuilder.setLanguage(language);
        userRequirementJsonBuilder.setReadMeTopics(readMe);
        userRequirementJsonBuilder.setUserLearningRequirement(userLearningRequirement);

        CodeBaseRequirements codeBaseRequirements = userRequirementJsonBuilder.build();
        assertNotNull(codeBaseRequirements);
        assertEquals(language, codeBaseRequirements.getLanguage());
        assertEquals(readMe, codeBaseRequirements.getReadMeTopics());
        assertEquals(userLearningRequirement.getContextOfLearning(), codeBaseRequirements.getUserLearningRequirement().getContextOfLearning());
        assertEquals(userLearningRequirement.getLearningLevel(), codeBaseRequirements.getUserLearningRequirement().getLearningLevel());
    }
}
