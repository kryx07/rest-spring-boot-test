package com.kryx07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;

public class JGitTest {


	File gitDirectory = new File("/pathname"); 
    //Git git = Git.init().setDirectory(gitDirectory);
	//Status status = git.status().call();


	public static void main(String[] args) {
		File file = new File ("test.txt");
		//Writer writer = null;
		try(Writer writer = new FileWriter(file,true);){
			writer.write("Stringi 25");
			writer.write(System.lineSeparator());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
