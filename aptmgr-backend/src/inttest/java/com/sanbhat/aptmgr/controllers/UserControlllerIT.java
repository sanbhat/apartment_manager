package com.sanbhat.aptmgr.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.models.Response;
import com.sanbhat.aptmgr.models.UserSearchResponse;

public class UserControlllerIT extends BaseControllerIT {

    public UserControlllerIT() {
		super(true);
	}

	@Test
    public void testGetByEmail() throws Exception {
    	HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
    	ResponseEntity<UserEntity> response = template.exchange(base.toString() + "users/email?email=test@gmail.com", 
    			HttpMethod.GET, httpEntity, UserEntity.class);
    	UserEntity user = response.getBody();
    	assertNotNull(user);
    	assertEquals("Test User", user.getName());
    }
    
    @Test
    public void testSearchQuery() throws Exception {
    	HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
    	ParameterizedTypeReference<Response<List<UserSearchResponse>>> p = new ParameterizedTypeReference<Response<List<UserSearchResponse>>>() {
		};
    	ResponseEntity<Response<List<UserSearchResponse>>> response = template.exchange(base.toString() + "users/search?query=test", 
    			HttpMethod.GET, httpEntity, p);
    	Response<List<UserSearchResponse>> searchResult = response.getBody();
    	assertNotNull(searchResult.getPayload());
    	assertEquals(1, searchResult.getPayload().size());
    	assertEquals("test@gmail.com", searchResult.getPayload().get(0).getEmail());
    }
}
