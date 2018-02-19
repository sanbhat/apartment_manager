package com.sanbhat.apartment.mgr.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testSave() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/save").contentType(MediaType.APPLICATION_JSON)
        		.content("{"+
							"\"id\": 0, " +
							"\"name\": \"Santhosh\"," +
							"\"age\": 31, "+
							"\"sex\": \"M\""+
    					  "}"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Employee data saved successfully!")));
    }
}