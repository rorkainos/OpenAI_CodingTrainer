package org.example.service;

import org.example.exceptions.DuplicatedFileException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/*
 *  The responsibility of this service is to create files / folders
 *  By default the name of the root dir is simply 'root'
 *  By default the path of the root directory is the users desktop
 *  rorourke 16.05.2023
 */
public final class FileService {
    public final static String DEFAULT_BASE_PATH = System.getProperty("user.home") + "/Desktop/";
    public final static String DEFAULT_ROOT_FOLDER_NAME = "root";
    private final File rootFolder;
    private final String baseURL;
    private Boolean deleteFolders;

    public FileService() throws IOException {
        this(DEFAULT_BASE_PATH, DEFAULT_ROOT_FOLDER_NAME);
    }

    public FileService(final String rootFolderName) throws IOException {
        this(DEFAULT_BASE_PATH, rootFolderName);
    }

    public FileService(final String baseDirectory, final String rootFolderName) throws IllegalArgumentException, IOException {
        Path directory = Paths.get(baseDirectory);
        this.deleteFolders = false;

        if (!(Files.exists(directory) && Files.isDirectory(directory)))
            throw new IllegalArgumentException("Directory doesn't exist: " + baseDirectory);

        this.baseURL = baseDirectory;
        this.rootFolder = createFolder(rootFolderName);
    }

    public void setParentFolderForDeletion(){
        this.rootFolder.deleteOnExit();
        this.deleteFolders = true;
    }


    public File createSubFolder(final File folder, final String folderName) throws IOException {
        String newFolderStringPath = folder.getAbsolutePath() + "/" + folderName;
        Path folderPath = Paths.get(newFolderStringPath);

        if(!Files.exists(folderPath) )
            Files.createDirectory(folderPath);

        File subFolder = new File(newFolderStringPath);
        subFolder.createNewFile();

        if(!subFolder.getParentFile().getAbsolutePath().equals(folder.getAbsolutePath()))
            throw new IOException("Something went wrong creating child folder");

        return subFolder;
    }

    public File createFileAndSubFolders(final String absolutePathOfFile, final String content) throws IOException {
        String[] folders = absolutePathOfFile.split("/");
        File currentFolder = getRootFolder();

        for (int i = 0; i < folders.length - 1; i++) {
            currentFolder = createSubFolder(currentFolder, folders[i]);

            if(deleteFolders)
                currentFolder.deleteOnExit();
        }

        File file = createFileInFolder(currentFolder, folders[folders.length - 1], content);
        if(deleteFolders)
            file.deleteOnExit();

        return file;
    }

    private boolean deleteFolder(){
        return this.deleteFolders;
    }

    public File createFileInFolder(final File folder, final String nameOfFile, final String Content) throws IOException {
        File file = new File(folder, nameOfFile);

        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Add content to the file
        bufferedWriter.write(Content);
        // Remember to close the writer
        bufferedWriter.close();
        return file;
    }

    public File getRootFolder(){
        return rootFolder;
    }

    private File createFolder(final String filePath) throws IOException {
        String fullFilePath = baseURL + filePath;
        Path folder = Paths.get(fullFilePath);
        Files.createDirectory(folder);

        return new File(fullFilePath);
    }
}
