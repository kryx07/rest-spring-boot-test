package com.kryx07.hellorest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public class FileControllerTest {

    private MockMvc mockMvc;

    @Test
    public void test() {
        System.out.println("Hello");
    }


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FileController())
                .build();


    }

    @Test
    public void testGet() throws Exception {
        mockMvc
                .perform(get("/file"))
                .andDo(print());
    }

    @Test
    public void testPost() throws Exception {
        mockMvc
                .perform(post("/file"))
                .andDo(print());
    }
}