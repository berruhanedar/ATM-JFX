package com.berru.app.atmjfx.iofiles;

/**
 * 📌 Basic interface for file operations
 * Every file management class is expected to implement this interface.
 */
public interface IFileHandlerInterface {

    /**
     * 📌 Creates the file if it doesn't exist, or opens it if it does.
     */
    void createFileIfNotExists();

    /**
     * 📌 Method to write data to the file.
     * @param data The data to be written.
     */
    void writeFile(String data);

    /**
     * 📌 Method to read data from the file.
     */
    void readFile();

    /**
     * 📌 Default method for logging.
     */
    default void logInfo(String message) {
        System.out.println("ℹ️ " + message);
    }
}
