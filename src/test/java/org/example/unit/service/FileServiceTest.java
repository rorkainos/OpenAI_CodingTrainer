package org.example.unit.service;


import org.example.ReadPropertyFile;
import org.example.exceptions.DuplicatedFileException;
import org.example.service.FileService;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/*
* Testing the FileService class
*
*  Test Coverage
*
*  1. Creating an instance of the service works thus setting the base url correctly
*  2. Testing that a basic txt file can be created
*  3. Testing that DuplicatedFileException is thrown when the same file is thrown twice.
*/
public class FileServiceTest {

    @Test
    public void testRetrieveBaseURL(){
        try{
            FileService fileService = new FileService();
        }catch (IOException e){
            fail("Could not get base url from properties.properties file: " +  e.getMessage());
        }
    }

   @Test
    public void testTextFileCreation() throws IOException, DuplicatedFileException {
        final String relativeFilePath = "test";
        final String fileType = "txt";
        final String fileName = relativeFilePath + "." + fileType;

        final FileService fileService = new FileService();
        final File textFile = fileService.createFile(relativeFilePath, fileType);
        final String filePath = ReadPropertyFile.getProjectBasePath() + fileName;

        assertNotNull(textFile);
        assertTrue(textFile.isFile());
        assertEquals(fileName, textFile.getName());
        assertEquals(filePath, textFile.getAbsolutePath());

        // clean up
        textFile.delete();
    }

    @Test
    public void testDuplicatedFileExceptionThrown() throws IOException, DuplicatedFileException {
        final String relativeFilePath = "test";
        final String fileType = "txt";

        final FileService fileService = new FileService();
        final File textFile = fileService.createFile(relativeFilePath,fileType);

        assertThrows(DuplicatedFileException.class, () -> {
            fileService.createFile(relativeFilePath, fileType);
        });

        // clean up
        textFile.delete();
    }
}
