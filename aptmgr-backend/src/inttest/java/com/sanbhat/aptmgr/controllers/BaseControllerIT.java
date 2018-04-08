package com.sanbhat.aptmgr.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.sanbhat.aptmgr.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerIT {
	
	@LocalServerPort
    private int port;

    protected URL base;

    @Autowired
    protected TestRestTemplate template;
    
    protected HttpHeaders httpHeaders = new HttpHeaders();
    
    private final static String DUMMY_USER = "test@gmail.com";
    
    public BaseControllerIT(boolean requiresAuthToken) {
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	if(requiresAuthToken) {
    		initAuthorizationHeader();
    	}
	}

    private void initAuthorizationHeader() {
    	String token = Jwts.builder()
	        	.setSubject(DUMMY_USER)
	            .claim(Constants.CLAIM_LOGIN_NAME, DUMMY_USER)
	            .claim(Constants.CLAIM_USER_ROLE, "SUPER_ADMIN")
	            .setIssuedAt(new Date())
	            .signWith(SignatureAlgorithm.HS256, Constants.JWT_KEY)
	            .compact();
    	this.httpHeaders.add("Authorization", "Bearer "+token);
	}

	@Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/aptmgr/api/");
    }

}
