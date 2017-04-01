package com.kryx07.hellorest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/file")
public class FileController {


    private final FileProcessor fileProcessor;

    public FileController(@Autowired FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void postLine(@RequestBody String string) {
        fileProcessor.saveToFile(string);
    }
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<String> getFileContents() {
        return fileProcessor.readFromFile();
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> clearFile() {
        return fileProcessor.clearFile() ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();

    }

}
