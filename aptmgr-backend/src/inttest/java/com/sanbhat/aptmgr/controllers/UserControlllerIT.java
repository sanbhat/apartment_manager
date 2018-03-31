package com.sanbhat.aptmgr.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.sanbhat.aptmgr.entities.UserEntity;

public class UserControlllerIT extends BaseControllerIT {

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
