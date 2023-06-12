package org.example.unit.service;


import org.example.DataGeneration.GenerateFiles;
import org.example.exceptions.DuplicatedFileException;
import org.example.service.FileService;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/*
* Testing the FileService class
*
* Test Coverage
* 1. creating the root directory
* 2. creating a sub folder in the root directory
* 3. creating a file in the root directory
* 4. creating two sub folders in the root directory
* 5. creating a file in each of the two sub Directories
* 6. custom name can be set for the default path of the directory which is the desktop
* 6. custom name of the root dir and custom path of the dir can be passed to the service
*/
public class FileServiceTest {


    public void testDefaultCallOfFileService_ParentDirectoryMade() {
        try{
            FileService fileService = new FileService();
            fileService.setParentFolderForDeletion();
            Path directory = Paths.get(FileService.DEFAULT_BASE_PATH + "/" + FileService.DEFAULT_ROOT_FOLDER_NAME) ;
            assertTrue(Files.exists(directory));
            assertTrue(Files.isDirectory(directory));
        }catch (IOException e) {
            fail("failed to created root test folder");
        }
    }


    public void testDefaultPath_CustomFolderNameCreated(){
        final String filename = "testDefaultPath_CustomFolderNameCreated";

        try{
            FileService fileService = new FileService(filename);

            // as this is a test we want this for cleanup
            fileService.setParentFolderForDeletion();
            Path directory = Paths.get(FileService.DEFAULT_BASE_PATH + "/" + filename) ;
            assertTrue(Files.exists(directory));
            assertTrue(Files.isDirectory(directory));
        }catch (IOException e) {
            fail("failed to create root folder with custom name");
        }
    }

    @Test
    public void testCustomBasePath_CustomFolderNameCreated(){
        final String filename = "testCustomBasePath_CustomFolderNameCreated";
        final String customFolderPath = "";

        try{
            FileService fileService = new FileService(customFolderPath, filename);

            // as this is a test we want this for cleanup
            fileService.setParentFolderForDeletion();
            Path directory = Paths.get(customFolderPath + filename) ;
            assertTrue(Files.exists(directory));
            assertTrue(Files.isDirectory(directory));
        }catch (IOException e) {
            fail("failed to create root folder with custom name");
        }
    }

    @Test
    public void testParentDir_FileCanBePlaced() throws IOException {
        final FileService fileService = new FileService("","testParentDir_FileCanBePlaced");
        fileService.setParentFolderForDeletion();

        final File rootFolder = fileService.getRootFolder();
        final String fileToBePlacedInFolder = "TestFileName.js";

        File file = fileService.createFileInFolder(rootFolder, fileToBePlacedInFolder, GenerateFiles.createJSFileString());
        file.deleteOnExit();

        assertTrue(rootFolder.exists());
        assertTrue(rootFolder.isDirectory());

        assertTrue(file.exists());
        assertEquals(fileToBePlacedInFolder, file.getName());
        assertEquals(rootFolder.getAbsolutePath(), file.getParentFile().getAbsolutePath());

    }

    @Test
    public void testParentFolder_SubFolderCreated() throws IOException {
        final FileService fileService = new FileService("","testParentFolder_SubFolderCreated");
        fileService.setParentFolderForDeletion();

        final File rootFolder = fileService.getRootFolder();
        final String subFolderName = "subFolder";

        File subFolder = fileService.createSubFolder(rootFolder, subFolderName);
        subFolder.deleteOnExit();

        assertTrue(subFolder.exists());
        assertTrue(subFolder.isDirectory());
        assertEquals(rootFolder.getAbsolutePath(), subFolder.getParentFile().getAbsolutePath());
    }

    @Test
    public void testParentFolder_TwoSubFoldersCreated() throws IOException {
        final FileService fileService = new FileService("","testParentFolder_TwoSubFoldersCreated");
        fileService.setParentFolderForDeletion();

        final File rootFolder = fileService.getRootFolder();
        final String subFolderNameOne = "subFolder1";
        final String subFolderNameTwo = "subFolder2";

        File subFolderOne = fileService.createSubFolder(rootFolder, subFolderNameOne);
        subFolderOne.deleteOnExit();

        File subFolderTwo = fileService.createSubFolder(rootFolder, subFolderNameTwo);
        subFolderTwo.deleteOnExit();

        assertTrue(subFolderOne.exists());
        assertTrue(subFolderOne.isDirectory());
        assertEquals(rootFolder.getAbsolutePath(), subFolderOne.getParentFile().getAbsolutePath());

        assertTrue(subFolderTwo.exists());
        assertTrue(subFolderTwo.isDirectory());
        assertEquals(rootFolder.getAbsolutePath(), subFolderTwo.getParentFile().getAbsolutePath());
    }

    @Test
    public void testSubFolder_FileCanBePlaced() throws IOException {
        final FileService fileService = new FileService("","testSubFolder_FileCanBePlaced");
        fileService.setParentFolderForDeletion();

        final String subFolderName = "subfolder";
        final String fileToBePlacedInFolder = "TestFileName.js";

        File subFolder = fileService.createSubFolder(fileService.getRootFolder(), subFolderName);
        subFolder.deleteOnExit();

        File file = fileService.createFileInFolder(subFolder, fileToBePlacedInFolder, GenerateFiles.createJSFileString());
        file.deleteOnExit();

        assertTrue(subFolder.exists());
        assertTrue(subFolder.isDirectory());

        assertTrue(file.exists());
        assertEquals(fileToBePlacedInFolder, file.getName());
        assertEquals(subFolder.getAbsolutePath(), file.getParentFile().getAbsolutePath());
    }


    @Test
    public void testTwoSubFolder_FilesCanBePlaced() throws IOException {
        final FileService fileService = new FileService("","testTwoSubFolder_FilesCanBePlaced");
        fileService.setParentFolderForDeletion();

        final String subFolderNameOne = "subfolderOne";
        final String subFolderNameTwo = "subfolderTwo";
        final String fileOneName = "TestFileNameOne.js";
        final String fileTwoName = "TestFileNameTwo.js";

        File subFolderOne = fileService.createSubFolder(fileService.getRootFolder(), subFolderNameOne);
        subFolderOne.deleteOnExit();

        File subFolderTwo = fileService.createSubFolder(fileService.getRootFolder(), subFolderNameTwo);
        subFolderTwo.deleteOnExit();

        File fileOne = fileService.createFileInFolder(subFolderOne, fileOneName, GenerateFiles.createJSFileString());
        fileOne.deleteOnExit();

        File fileTwo = fileService.createFileInFolder(subFolderTwo, fileTwoName, GenerateFiles.createJSFileString());
        fileTwo.deleteOnExit();

        assertTrue(fileOne.exists());
        assertEquals(fileOneName, fileOne.getName());
        assertEquals(subFolderOne.getAbsolutePath(), fileOne.getParentFile().getAbsolutePath());

        assertTrue(fileTwo.exists());
        assertEquals(fileTwoName, fileTwo.getName());
        assertEquals(subFolderTwo.getAbsolutePath(), fileTwo.getParentFile().getAbsolutePath());
    }
}
