package org.example.service;

import org.example.ReadPropertyFile;
import org.example.exceptions.DuplicatedFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/*
 *  The responsibility of this service is to simply create files of any sensible type based.
 *  In the case where a duplicated file is created an DuplicatedFileException is thrown.
 *
 *  rorourke 16.05.2023
 */
public class FileService {
    private final String baseURL;

    public FileService() throws IOException {
        this.baseURL = ReadPropertyFile.getProjectBasePath();
    }

    public File createFile(final String fileRelativePath, final String filetype) throws IOException, DuplicatedFileException {
        final String filePath = baseURL + fileRelativePath + "." + filetype;
        File file = new File(filePath);

        if (!file.createNewFile())
            throw new DuplicatedFileException(filePath);

        return file;
    }
}
