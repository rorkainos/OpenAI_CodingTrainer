package org.example.unit.service;


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
    public void testCustomBaseDirectoryPassed(){
        try{
            String filePath = System.getProperty("user.home") + "/Desktop/";
            FileService fileService = new FileService(filePath);
        }catch (IllegalArgumentException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testCustomBaseDirectoryFailed(){
        try{
            String filePath = "dcdcadac";
            FileService fileService = new FileService(filePath);
            fail("Illegal Argument exception should have been thrown !");
        }catch (IllegalArgumentException e){
           // Pass
        }
    }

   @Test
    public void testTextFileCreation() throws IOException, DuplicatedFileException {
        final String relativeFilePath = "test1234";
        final String fileType = "txt";
        final String fileName = relativeFilePath + "." + fileType;
        final String filePath = System.getProperty("user.home") + "/Desktop/";

        final FileService fileService = new FileService(filePath);
        final File textFile = fileService.createFile(relativeFilePath, fileType);

        try{
            assertNotNull(textFile);
            assertTrue(textFile.isFile());
            assertEquals(fileName, textFile.getName());
            assertEquals(filePath + fileName, textFile.getAbsolutePath());
        }finally {
            // Clean up
            if (textFile != null && textFile.exists())
                textFile.delete();
        }
    }

    @Test
    public void testDuplicatedFileExceptionThrown() throws IOException, DuplicatedFileException {
        final String fileName = "test12345";
        final String fileType = "txt";

        final FileService fileService = new FileService();
        final File textFile = fileService.createFile(fileName, fileType);

        try{
            assertThrows(DuplicatedFileException.class, () -> {
                fileService.createFile(fileName, fileType);
            });
        }finally {
            // Clean up
            if (textFile != null && textFile.exists())
                textFile.delete();
        }
    }
}
