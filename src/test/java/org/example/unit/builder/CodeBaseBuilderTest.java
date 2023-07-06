package org.example.unit.builder;

import org.example.Builder.CodeBaseBuilder;
import org.example.Builder.UserRequirementJsonBuilder;
import org.example.DataGeneration.GenerateFiles;
import org.example.azure.AzureFileCreator;
import org.example.azure.AzureProgramDetails;
import org.example.exceptions.InvalidResponseException;
import org.example.exceptions.PropertyNotFoundException;
import org.example.models.CodeBaseRequirements;
import org.example.properties.PropertiesExtractor;
import org.example.service.FileService;
import org.example.validators.AzureValidation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CodeBaseBuilderTest {

    @Mock
    private AzureFileCreator azureFileCreator;
    private CodeBaseBuilder codeBaseBuilder;
    private final static CodeBaseRequirements CODE_BASE_REQUIREMENTS = new CodeBaseRequirements("", "", "", "", 0);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        codeBaseBuilder = new CodeBaseBuilder(CODE_BASE_REQUIREMENTS, azureFileCreator);
    }
    
    public void testSimpleBuild() throws IOException, InvalidResponseException {
        List<String> filesWithAbsolutePath = List.of("package.json", "subfolder1/index.html", "subfolder2/move.js", "homePage.css");
        List<String> fileContents = List.of(GenerateFiles.createJSONFileString(), GenerateFiles.createHTMLFileString(),
                GenerateFiles.createJSFileString(), GenerateFiles.createCSSFileString());

        when(azureFileCreator.getProgramDetails(CODE_BASE_REQUIREMENTS)).thenReturn(new AzureProgramDetails(filesWithAbsolutePath, fileContents));
        codeBaseBuilder.build("", "testSimpleBuild");


    }
}
