package org.example.azure;

import org.example.models.CodeBaseRequirements;
import org.example.properties.AzureProperties;

import java.util.List;

public class AzureFileCreator extends AzureCaller {

    public AzureFileCreator(AzureProperties azureProperties) {
        super(azureProperties);
    }

    public AzureProgramDetails getProgramDetails(CodeBaseRequirements codeBase){
//        // two calls to azure one to ge the file names and one to get the file content
//        List<String> filesWithAbsolutePath = List.of("package.json", "subfolder1/index.html", "subfolder2/move.js", "homePage.css");
////        List<String> fileContents = List.of("content1", "content2", "content3", "content4");
//         return new AzureProgramDetails(filesWithAbsolutePath, fileContents);
        return null;
    }

}
