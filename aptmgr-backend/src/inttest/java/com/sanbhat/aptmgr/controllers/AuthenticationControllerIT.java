package com.sanbhat.aptmgr.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.models.Response;

public class AuthenticationControllerIT extends BaseControllerIT {

    @Test
    public void testSignUp() throws Exception {
    	UserEntity user = new UserEntity();
    	user.setEmail("bhat.86@gmail.com");
    	user.setName("Santhosh Bhat");
    	user.setPassword("password");
    	
    	HttpEntity<UserEntity> httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<Response> response = template.exchange(base.toString() + "auth/signup/", HttpMethod.POST, httpEntity, Response.class);
        Response<String> resp = response.getBody();
        assertThat(resp.getErrorCode(), equalTo("USER_EXISTS"));
        assertThat(resp.getErrorMessage(), equalTo("User with email bhat.86@gmail.com is already registered with us. Please use another email."));
    }

}
