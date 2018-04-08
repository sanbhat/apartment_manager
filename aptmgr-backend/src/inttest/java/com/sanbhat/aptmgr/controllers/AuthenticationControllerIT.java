package com.sanbhat.aptmgr.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.models.Response;

public class AuthenticationControllerIT extends BaseControllerIT {

    public AuthenticationControllerIT() {
		super(false);
	}

	@Test
    public void testSignUp() throws Exception {
    	UserEntity user = new UserEntity();
    	user.setEmail("test@gmail.com");
    	user.setName("Test User");
    	user.setPassword("password");
    	user.setCountryCode(91);
    	user.setPhone("1234567890");
    	user.setRole("SUPER_ADMIN");
    	ParameterizedTypeReference<Response<String>> p = new ParameterizedTypeReference<Response<String>>() {
		};
    	
    	HttpEntity<UserEntity> httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<Response<String>> response = template.exchange(base.toString() + "auth/signup/", HttpMethod.POST, httpEntity, p);
        Response<String> resp = response.getBody();
        assertThat(resp.getErrorCode(), equalTo("USER_EXISTS"));
        assertThat(resp.getErrorMessage(), equalTo("User with email test@gmail.com is already registered with us. Please use another email."));
    }

}
