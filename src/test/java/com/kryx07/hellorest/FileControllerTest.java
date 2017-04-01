package com.kryx07.hellorest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.jboss.logging.Param;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JUnitParamsRunner.class)
public class FileControllerTest {


    private MockMvc mockMvc;

   /* @Test
    public void test() {
        System.out.println("Hello");
    }*/

    @Mock
    private FileProcessor fileProcessor;

    //FileController fileController = new FileController(fileProcessor);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FileController(fileProcessor))
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

    @Test
    @Parameters({
            "Pieski dwa",
            "Kotki dwa"
    })
    public void testPost(String text) throws Exception {
        Mockito.when(fileProcessor.clearFile()).thenReturn(true);
        mockMvc
                .perform(delete("/file"))
                .andExpect(status().isOk());

        Mockito.when(fileProcessor.clearFile()).thenReturn(true);
        Mockito.when(fileProcessor.saveToFile(text)).thenReturn(true);
        mockMvc
                .perform(post("/file").content(text))
                .andExpect(status().isCreated());

        Mockito.verify(fileProcessor,Mockito.times(1)).saveToFile(text);

        Mockito.when(fileProcessor.readFromFile()).thenReturn(Arrays.asList(text));
        mockMvc
                .perform(get("/file"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", equalTo(text)));
    }
}