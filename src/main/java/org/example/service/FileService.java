package org.example.service;

import org.example.exceptions.DuplicatedFileException;
import org.example.exceptions.PropertyNotFoundException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/*
 *  The responsibility of this service is to simply create files of any sensible type based.
 *  In the case where a duplicated file is created an DuplicatedFileException is thrown.
 *
 *  rorourke 16.05.2023
 */
public class FileService {
    private final String baseURL;

    public FileService() {
        this.baseURL = System.getProperty("user.home") + "/Desktop/";
    }

    public FileService(String baseDirectory) throws IllegalArgumentException {
        Path directory = Paths.get(baseDirectory);

        if (!(Files.exists(directory) && Files.isDirectory(directory)))
            throw new IllegalArgumentException("Director doesn't exist: " + baseDirectory);

        this.baseURL = baseDirectory;
    }


    public File createFile(final String fileName, final String fileContent) throws IOException, DuplicatedFileException {
        File file = getNewFile(baseURL + fileName);

        try (FileWriter fileWriter = new FileWriter(file)) {
            // Write HTML code to the file using the FileWriter instance
            fileWriter.write(fileContent);
        }

        return file;
    }

    private File getNewFile(final String fileAbsolutePathName) throws IOException, DuplicatedFileException {

        File file = new File(fileAbsolutePathName);

        if (!file.createNewFile())
            throw new DuplicatedFileException(fileAbsolutePathName);

        return file;
    }
}
