package com.sanbhat.aptmgr.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.sanbhat.aptmgr.entities.ApartmentsEntity;
import com.sanbhat.aptmgr.models.Apartment;
import com.sanbhat.aptmgr.models.Response;

public class ApartmentControlllerIT extends BaseControllerIT {

    public ApartmentControlllerIT() {
		super(true);
	}

	@Test
    public void testSave() throws Exception {
    	ApartmentsEntity apt = new ApartmentsEntity();
    	apt.setAptName("Test Apartment");
    	apt.setAddress("Test Address, Test Locality");
    	apt.setCity("Test City");
    	apt.setPinCode("123456");
    	apt.setPrimaryEmail("test@gmail.com");
    	
    	HttpEntity<ApartmentsEntity> httpEntity = new HttpEntity<>(apt, httpHeaders);
    	ParameterizedTypeReference<Response<String>> p = new ParameterizedTypeReference<Response<String>>() {
		};
    	ResponseEntity<Response<String>> response = template.exchange(base.toString() + "apartment/save/", HttpMethod.POST, httpEntity, p);
        System.out.println(response.getBody());
        assertThat(response.getBody().getMessage(), equalTo("Apartment has been successfully registered."));
    }
    
    @Test
    public void testGet() throws Exception {
    	HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
    	ResponseEntity<Apartment> response = template.exchange(base.toString() + "apartment/get/1", HttpMethod.GET, httpEntity, Apartment.class);
    	Apartment apt = response.getBody();
    	assertNotNull(apt);
    	assertEquals(apt.getName(), "Test Apartment");
    }
}
