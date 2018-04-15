package com.sanbhat.aptmgr.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.sanbhat.aptmgr.models.Apartment;
import com.sanbhat.aptmgr.models.ApartmentUserRoleMapping;
import com.sanbhat.aptmgr.models.Response;
import com.sanbhat.aptmgr.models.User;
import com.sanbhat.aptmgr.services.ApartmentsService;

public class ApartmentControlllerIT extends BaseControllerIT {

    public ApartmentControlllerIT() {
		super(true);
	}
    
    @Autowired
    private ApartmentsService apartmentService;

	@Test
    public void testSave() throws Exception {
    	Apartment apt = new Apartment();
    	apt.setAptName("Test Apartment");
    	apt.setAddress("Test Address, Test Locality");
    	apt.setCity("Test City");
    	apt.setPinCode("123456");
    	apt.setPrimaryEmail("test@gmail.com");
    	
    	HttpEntity<Apartment> httpEntity = new HttpEntity<>(apt, httpHeaders);
    	ParameterizedTypeReference<Response<String>> p = new ParameterizedTypeReference<Response<String>>() {
		};
    	ResponseEntity<Response<String>> response = template.exchange(base.toString() + "apartment/save/", HttpMethod.POST, httpEntity, p);
        System.out.println(response.getBody());
        assertThat(response.getBody().getMessage(), equalTo("Apartment has been successfully registered."));
    }
    
    @Test
    public void testGet() throws Exception {
    	HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
    	ResponseEntity<Apartment> response = template.exchange(base.toString() + "apartment/get/22", HttpMethod.GET, httpEntity, Apartment.class);
    	Apartment apt = response.getBody();
    	assertNotNull(apt);
    	assertEquals(apt.getAptName(), "Test Apartment");
    }
    
    @Test
    public void testUpdate() throws Exception {
    	Apartment apt = apartmentService.getApartmentById(22);
    	
    	for(ApartmentUserRoleMapping mapping : apt.getApartmentRoleMappings()) {
    		if(mapping.getRole().getRoleId() == 1) {
    			User user = new User();
    			user.setId(23);
    			user.setEmail("test@gmail.com");
    			user.setName("Test User");
    			mapping.setUser(user);
    		}
    	}
    	
    	HttpEntity<Apartment> httpEntity = new HttpEntity<>(apt, httpHeaders);
    	ParameterizedTypeReference<Response<String>> p = new ParameterizedTypeReference<Response<String>>() {
		};
    	ResponseEntity<Response<String>> response = template.exchange(base.toString() + "apartment/update/", HttpMethod.PUT, httpEntity, p);
        System.out.println(response.getBody());
        assertThat(response.getBody().getMessage(), equalTo("Apartment info has been successfully updated."));
        
        apt = apartmentService.getApartmentById(22);
    	
    	for(ApartmentUserRoleMapping mapping : apt.getApartmentRoleMappings()) {
    		if(mapping.getRole().getRoleId() == 2) {
    			User user = new User();
    			user.setId(1);
    			user.setEmail("bhat.86@gmail.com");
    			user.setName("Santhosh Bhat");
    			mapping.setUser(user);
    		}
    	}
    	
    	httpEntity = new HttpEntity<>(apt, httpHeaders);
    	response = template.exchange(base.toString() + "apartment/update/", HttpMethod.PUT, httpEntity, p);
        System.out.println(response.getBody());
        assertThat(response.getBody().getMessage(), equalTo("Apartment info has been successfully updated."));
    }
}
