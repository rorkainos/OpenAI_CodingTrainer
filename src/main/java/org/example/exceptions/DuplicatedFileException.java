package org.example.exceptions;

/*
 * When a duplicated file is created in the FileService#createFile method this exception is
 * thrown.
 *
 * rorourke 16.05.2023
 */
public class DuplicatedFileException extends Exception{
    private final String filePath;

    public DuplicatedFileException(final String filePath){
        super();
        this.filePath = filePath;
    }

    @Override
    public String getMessage() {
        return "File already exists: " + filePath;
    }
}
