package com.example.returnkey.returnmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.returnkey.returnmanagement.controller.ReturnController;
import com.example.returnkey.returnmanagement.controller.UploadController;
import com.example.returnkey.returnmanagement.service.impl.ReturnService;
import com.example.returnkey.returnmanagement.service.impl.UploadService;

@SpringBootTest
class ReturnManagementApplicationTests {

	@Autowired
    private UploadController uploadController;

	@Autowired
    private ReturnController returnController;


	@Autowired
    private ReturnService returnService;

	@Autowired
    private UploadService uploadService;

	@Test
	void contextLoads() {
		assertThat(uploadController).isNotNull();
		assertThat(returnController).isNotNull();
		assertThat(uploadService).isNotNull();
		assertThat(returnService).isNotNull();
	}

}
