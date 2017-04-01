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


    public void saveToFile(String string) {
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(string + System.lineSeparator());
            writer.flush();
        }  catch (IOException e) {
            e.printStackTrace();
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
        File file = new File(fileName);
        if(!file.exists()){
            return false;
        } else{
            file.delete();
            return true;
        }
    }
}
