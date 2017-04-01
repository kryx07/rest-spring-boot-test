package com.kryx07.hellorest;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class FileControllerTest {

    FileController fileController = new FileController(new FileProcessor());

    private MockMvc mockMvc;

   /* @Test
    public void test() {
        System.out.println("Hello");
    }*/


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(fileController)
                .build();


    }

   /* @Test
    public void testGet() throws Exception {
        mockMvc
                .perform(get("/file"))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$",equalTo("[\"hello\"],[\test\"]")))
                .andExpect(jsonPath("$[0]", equalTo("hello")))
                .andExpect(jsonPath("$[1]",equalTo("testWorld")));

    }*/
   @Autowired
    @Test
    public void testPost() throws Exception {
        fileController.clearFile();

        mockMvc
                .perform(post("/file").content("dupa2"))
                .andExpect(status().isCreated());

        mockMvc
                .perform(get("/file"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", equalTo("dupa2")));
    }
}