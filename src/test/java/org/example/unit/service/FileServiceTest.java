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
            FileService fileService = new FileService("");
        }catch (IllegalArgumentException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testCustomBaseDirectoryExceptionShouldThrow(){
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
        final String fileName = "test1234.txt";

        final FileService fileService = new FileService("");
        final File textFile = fileService.createFile(fileName, "");

        try{
            assertNotNull(textFile);
            assertTrue(textFile.isFile());
            assertEquals(fileName, textFile.getName());
        }finally {
            // Clean up
            if (textFile != null && textFile.exists())
                textFile.delete();
        }
    }

    @Test
    public void testDuplicatedFileExceptionThrown() throws IOException, DuplicatedFileException {
        final String fileName = "test12345.txt";

        final FileService fileService = new FileService("");
        final File textFile = fileService.createFile(fileName, "");

        try{
            assertThrows(DuplicatedFileException.class, () -> {
                fileService.createFile(fileName, "");
            });
        }finally {
            // Clean up
            if (textFile != null && textFile.exists())
                textFile.delete();
        }
    }
}
