package com.kryx07.hellorest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
public class FileController {

    final private String fileName = "file.txt";

    @RequestMapping(value = "/file", method = RequestMethod.POST, consumes = "application/json")
    public void postLine(@RequestParam(value = "string", defaultValue = "World") String string) {
        try (Writer writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(string + System.lineSeparator());
            writer.flush();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/file", method = RequestMethod.GET, produces = "application/json")
    public List<String> getFileContents() {
        List<String> lineList = null;
        try {
            lineList = Files.readAllLines(Paths.get(fileName)).stream().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineList;
    }
}