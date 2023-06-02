package org.example.unit.service;

import org.example.exceptions.PropertyNotFoundException;
import org.example.service.ReadPropertyFileService;
import org.example.exceptions.DuplicatedFileException;
import org.example.service.FileService;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
    public void testTextFileCreationWithContent() throws IOException, DuplicatedFileException {
        final String fileName = "test1234.txt";
        final String fileContent = "Random Text \n random text";

        final FileService fileService = new FileService("");
        final File textFile = fileService.createFile(fileName, fileContent);

        try{
            assertNotNull(textFile);
            assertTrue(textFile.isFile());
            assertEquals(fileName, textFile.getName());

            final String contentFromFile = Files.readString(Path.of(fileName));
            assertEquals(fileContent, contentFromFile);
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

    @Test
    public void testHTMLFileCreation() throws IOException, DuplicatedFileException {
        final String fileName = "test1234.html";

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
    public void testHTMLFileCreationWithContent() throws IOException, DuplicatedFileException {
        StringBuilder sb = new StringBuilder();
        // Start HTML document
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>Generated HTML</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");

        // Add content to the HTML body
        sb.append("<h1>Hello, world!</h1>\n");
        sb.append("<p>This is a generated HTML document.</p>\n");

        // End HTML document
        sb.append("</body>\n");
        sb.append("</html>");

        final String fileContent = sb.toString();
        final String fileName = "test1234.html";
        final FileService fileService = new FileService("");
        final File textFile = fileService.createFile(fileName, fileContent);

        try{
            assertNotNull(textFile);
            assertTrue(textFile.isFile());
            assertEquals(fileName, textFile.getName());

            final String contentFromFile = Files.readString(Path.of(fileName));
            assertEquals(fileContent, contentFromFile);
        }finally {
            // Clean up
            if (textFile != null && textFile.exists())
                textFile.delete();
        }
    }

    @Test
    public void testCssFileCreation() throws IOException, DuplicatedFileException {
        final String fileName = "test1234.css";

        final FileService fileService = new FileService("");
        final File cssFile = fileService.createFile(fileName, "");

        try {
            assertNotNull(cssFile);
            assertTrue(cssFile.isFile());
            assertEquals(fileName, cssFile.getName());
        } finally {
            // Clean up
            if (cssFile != null && cssFile.exists())
                cssFile.delete();
        }
    }

    @Test
    public void testCssFileCreationWithContent() throws IOException, DuplicatedFileException {
        final String fileContent = "body { color: blue; }";
        final String fileName = "test1234.css";

        final FileService fileService = new FileService("");
        final File cssFile = fileService.createFile(fileName, fileContent);

        try {
            assertNotNull(cssFile);
            assertTrue(cssFile.isFile());
            assertEquals(fileName, cssFile.getName());

            final String contentFromFile = Files.readString(cssFile.toPath());
            assertEquals(fileContent, contentFromFile);
        } finally {
            // Clean up
            if (cssFile != null && cssFile.exists())
                cssFile.delete();
        }
    }

    @Test
    public void testJsonFileCreation() throws IOException, DuplicatedFileException {
        final String fileName = "test1234.json";

        final FileService fileService = new FileService("");
        final File jsonFile = fileService.createFile(fileName, "");

        try {
            assertNotNull(jsonFile);
            assertTrue(jsonFile.isFile());
            assertEquals(fileName, jsonFile.getName());
        } finally {
            // Clean up
            if (jsonFile != null && jsonFile.exists())
                jsonFile.delete();
        }
    }

    @Test
    public void testJsonFileCreationWithContent() throws IOException, DuplicatedFileException {
        final String fileContent = "{ \"key\": \"value\" }";
        final String fileName = "test1234.json";

        final FileService fileService = new FileService("");
        final File jsonFile = fileService.createFile(fileName, fileContent);

        try {
            assertNotNull(jsonFile);
            assertTrue(jsonFile.isFile());
            assertEquals(fileName, jsonFile.getName());

            final String contentFromFile = Files.readString(jsonFile.toPath());
            assertEquals(fileContent, contentFromFile);
        } finally {
            // Clean up
            if (jsonFile != null && jsonFile.exists())
                jsonFile.delete();
        }
    }

    @Test
    public void testJavaScriptFileCreation() throws IOException, DuplicatedFileException {
        final String fileName = "test1234.js";

        final FileService fileService = new FileService("");
        final File jsFile = fileService.createFile(fileName, "");

        try {
            assertNotNull(jsFile);
            assertTrue(jsFile.isFile());
            assertEquals(fileName, jsFile.getName());
        } finally {
            // Clean up
            if (jsFile != null && jsFile.exists())
                jsFile.delete();
        }
    }

    @Test
    public void testJavaScriptFileCreationWithContent() throws IOException, DuplicatedFileException {
        final String fileContent = "function greet() {\n" +
                "    console.log('Hello, World!');\n" +
                "}\n" +
                "\n" +
                "greet();";

        final String fileName = "test1234.js";

        final FileService fileService = new FileService("");
        final File jsFile = fileService.createFile(fileName, fileContent);

        try {
            assertNotNull(jsFile);
            assertTrue(jsFile.isFile());
            assertEquals(fileName, jsFile.getName());

            final String contentFromFile = Files.readString(jsFile.toPath());
            assertEquals(fileContent, contentFromFile);
        } finally {
            // Clean up
            if (jsFile != null && jsFile.exists())
                jsFile.delete();
        }
    }
}
