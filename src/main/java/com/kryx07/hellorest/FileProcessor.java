package com.kryx07.hellorest;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileProcessor {
    final private String fileName = "file.txt";
    File file = new File(fileName);

    public boolean saveToFile(String string) {
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(string + System.lineSeparator());
            writer.flush();
            return true;
        }  catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> readFromFile() {
        List<String> lineList = null;
        try {
            lineList = Files.readAllLines(Paths.get(fileName)).stream().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineList;
    }

    public boolean clearFile() {
        return file.exists() ? deleteFile() : false;
    }

    private boolean deleteFile(){
        try {
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
