package com.sanbhat.aptmgr.controllers;

import java.net.URL;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerIT {
	
	@LocalServerPort
    private int port;

    protected URL base;

    @Autowired
    protected TestRestTemplate template;
    
    protected HttpHeaders httpHeaders = new HttpHeaders();

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/");
    }

}
