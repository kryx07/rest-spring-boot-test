package com.kryx07.hellorest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RunWith(JUnit4.class)
public class FileProcessorTest {

    FileProcessor fileProcessor = new FileProcessor();
    File file = new File("file.txt");

    @Test
    public void checkIfFileExistsAfterWriting() {
        file.delete();
        fileProcessor.saveToFile("hello");
        Assert.assertEquals(true,file.exists());
    }
    /*public List<String> readFromFile() throws IOException {
        Scanner scanner =null;
        scanner = new Scanner (new FileReader(file));
        int i = 0;
        List<String> list = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        for(String br:bufferedReader)
        String expected = bufferedReader.readLine();
        bufferedReader.close();


        while(scanner.hasNext()){
            list.set(i++,scanner.nextLine());
        }

        scanner.close();
        return list;
    }*/

    @Test
    public void checkIfFileIsWrittenWithCorrectContent() throws FileNotFoundException {
        file.delete();
        List<String> expectedList = Arrays.asList("cos","fds","gkjh");

        for(int i= 0; i<expectedList.size(); i++){
            fileProcessor.saveToFile(expectedList.get(i));
        }

        List<String> actualList = fileProcessor.readFromFile();

        Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void deletingTheFileWhenExists() throws IOException {
        file.delete();
        file.createNewFile();
        fileProcessor.clearFile();
        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void deletingTheFileWhenNotExists() {
        file.delete();
        fileProcessor.clearFile();
        Assert.assertEquals(false, file.exists());
    }

    @Test
    public void readFromFileTest() throws IOException {
        file.delete();
        PrintWriter printWriter = new PrintWriter(file);
        String text = "hello";
        printWriter.println(text);
        printWriter.flush();
        printWriter.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String expected = bufferedReader.readLine();
        bufferedReader.close();

        String actual = fileProcessor.readFromFile().get(0);

        Assert.assertEquals(expected,actual);
    }
}
