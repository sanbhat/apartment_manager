package com.sanbhat.aptmgr.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sanbhat.aptmgr.entities.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControlllerIT {

	@LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;
    
    private HttpHeaders httpHeaders = new HttpHeaders();

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void testSignUp() throws Exception {
    	UserEntity user = new UserEntity();
    	user.setEmail("bhat.86@gmail.com");
    	user.setName("Santhosh Bhat");
    	user.setPassword("password");
    	
    	HttpEntity<UserEntity> httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> response = template.exchange(base.toString() + "users/signup/", HttpMethod.POST, httpEntity, String.class);
        System.out.println(response.getBody());
        assertThat(response.getBody(), equalTo("SIGNUP_SUCCESSFUL"));
    }
    
    @Test
    public void testGetByEmail() throws Exception {
    	HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
    	ResponseEntity<UserEntity> response = template.exchange(base.toString() + "users/email?email=bhat.86@gmail.com", 
    			HttpMethod.GET, httpEntity, UserEntity.class);
    	UserEntity user = response.getBody();
    	assertNotNull(user);
    	assertEquals(user.getName(), "Santhosh Bhat");
    }
}
