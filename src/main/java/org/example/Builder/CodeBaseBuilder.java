package org.example.Builder;

import org.example.azure.AzureCaller;
import org.example.azure.AzureFileCreator;
import org.example.azure.AzureProgramDetails;
import org.example.properties.AzureProperties;
import org.example.models.CodeBaseRequirements;
import org.example.service.FileService;

import java.io.IOException;
import java.util.List;

/*
* class that will serve as our class that builds the code base.
* this needs to be extended to call azure
*/
public class CodeBaseBuilder {
    private CodeBaseRequirements codeBaseRequirements;
    private AzureFileCreator fileCreator;
    private FileService fileService;

    public CodeBaseBuilder(CodeBaseRequirements codeBaseRequirements, AzureFileCreator fileCreator) {
        this.codeBaseRequirements = codeBaseRequirements;
        this.fileCreator = fileCreator;
    }

    public void build(final String rootDir, final String nameOfDir) throws IOException {
        fileService = new FileService(rootDir, nameOfDir);
        AzureProgramDetails programDetails = fileCreator.getProgramDetails(codeBaseRequirements);

        for (int i = 0; i < programDetails.filesAbsolutePaths().size(); i++) {
            String filePath = programDetails.filesAbsolutePaths().get(i);
            String fileContent = programDetails.fileContents().get(i);
            fileService.createFileAndSubFolders(filePath, fileContent);
        }
    }
}
