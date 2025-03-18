package com.berru.app.atmjfx.iofiles;

import com.berru.app.atmjfx.utils.SpecialColor;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class SpecialFileHandler {

    private static final Logger logger = Logger.getLogger(SpecialFileHandler.class.getName());
    private String filePath;

    // Default constructor sets the default file path
    public SpecialFileHandler() {
        this.filePath = "default.txt";  // Default file name
    }

    // Creates the file if it doesn't already exist
    public void createFileIfNotExists() {
        File file = new File(filePath);
        try {
            if (file.exists()) {
                // File already exists
                System.out.println(SpecialColor.YELLOW+"✅ File already exists: " + filePath+SpecialColor.RESET);
            } else {
                if (file.createNewFile()) {
                    // File created successfully
                    System.out.println(SpecialColor.BLUE+"📄 New file created: " + filePath+SpecialColor.RESET);
                } else {
                    // Failed to create file
                    System.out.println(SpecialColor.RED+"⚠️ File could not be created: " + filePath+SpecialColor.RESET);
                }
            }
        } catch (IOException e) {
            // Error during file creation
            System.out.println(SpecialColor.RED+"⚠️ File creation error: " + filePath+SpecialColor.RESET);
        }
    }

    // Writes data to the file
    public void writeFile(String data) {
        if (data == null || data.trim().isEmpty()) {
            // Data cannot be empty
            System.out.println(SpecialColor.RED+"⚠️ Empty data cannot be written! " + filePath+SpecialColor.RESET);
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
            // Data written successfully
            System.out.println(SpecialColor.BLUE+"✅ Data successfully written to the file: " + filePath+SpecialColor.RESET);
        } catch (IOException e) {
            // Error during writing data
            System.out.println(SpecialColor.RED+"❌ File write error: " + filePath+SpecialColor.RESET);
        }
    }

    // Reads the contents of the file
    public List<String> readFile() {
        File file = new File(filePath);
        List<String> fileLines = new ArrayList<>();

        if (!file.exists()) {
            // File not found
            System.out.println(SpecialColor.RED+"⚠️ File not found: " + filePath+SpecialColor.RESET);
            return fileLines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println(SpecialColor.BLUE+"📖 Reading file content... " +SpecialColor.RESET);
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            // Error during reading file
            System.out.println(SpecialColor.RED+"❌ File read error: " + filePath+SpecialColor.RESET);
        }

        if (fileLines.isEmpty()) {
            // File is empty
            System.out.println(SpecialColor.RED+"⚠️ File is empty despite being read." +SpecialColor.RESET);
        } else {
            // Data successfully read from the file
            System.out.println(SpecialColor.BLUE+"✅ Successfully read " + fileLines.size() + " lines from the file."+SpecialColor.RESET);
        }

        return fileLines;
    }

    // Getter for file path
    public String getFilePath() {
        return filePath;
    }

    // Setter for file path
    public void setFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            // Invalid file path, setting default
            System.out.println(SpecialColor.RED+"⚠️ Invalid file path! Default file name is assigned: default.txt"+SpecialColor.RESET);
            this.filePath = "default.txt";
        } else {
            this.filePath = filePath;
        }
    }
}
