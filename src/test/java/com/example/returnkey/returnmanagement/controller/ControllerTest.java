package com.example.returnkey.returnmanagement.controller;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @Order(1)
    @Test
    public void handleFileUploadTest() throws Exception {

        File resource = new ClassPathResource("data/orders.csv").getFile();
        byte[] content = Files.readAllBytes(resource.toPath());
        MockMultipartFile csvFile = new MockMultipartFile("file", "orders.csv", "text/plain", content);

        // test upload first time
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
            .file(csvFile))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("upload successful")));

        // test reupload same file
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
            .file(csvFile))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("data already exists")));
    }

    @Order(2)
    @Test
    public void handleInquiryReturnTest() throws Exception {

        File resource = new ClassPathResource("data/orders.csv").getFile();
        byte[] content = Files.readAllBytes(resource.toPath());
        MockMultipartFile csvFile = new MockMultipartFile("file", "orders.csv", "text/plain", content);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
            .file(csvFile))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("upload successful")));

        mockMvc.perform(post("/pending/returns")
            .param("email", "carly@example.com")
            .param("orderId", "RK-238"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("d521ae99bfa4c7004e139f9bf1a31760df1822b944bb0499954476bb5ec633eb")));
    }

    @Order(3)
    @Test
    public void handleCreateReturnTest() throws Exception {

        handleInquiryReturnTest();

        mockMvc.perform(post("/returns")
            .param("token", "d521ae99bfa4c7004e139f9bf1a31760df1822b944bb0499954476bb5ec633eb")
            ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("carly@example.com")));
    }

    @Order(4)
    @Test
    public void handleGetReturnTest() throws Exception {

        handleCreateReturnTest();

        mockMvc.perform(get("/returns/1")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("carly@example.com")));
    }

    @Order(5)
    @Test
    public void handleSetItemQCTest() throws Exception {

        handleCreateReturnTest();

        mockMvc.perform(put("/returns/1/items/1/qc/status")
            .param("status", "ACCEPTED") 
            ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("ACCEPTED")));

    }

}