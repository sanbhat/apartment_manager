package com.sanbhat.aptmgr.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.sanbhat.aptmgr.Constants;
import com.sanbhat.aptmgr.entities.ApartmentsEntity;

public class ApartmentControlllerIT extends BaseControllerIT {

    @Test
    public void testSave() throws Exception {
    	ApartmentsEntity apt = new ApartmentsEntity();
    	apt.setAptName("Shilpitha Splendour Annex");
    	apt.setAddress("Chinnappa Layout, Mahadevapura");
    	apt.setCity("Bengaluru");
    	apt.setPinCode("560048");
    	apt.setPrimaryEmail("surendra@gmail.com");
    	
    	HttpEntity<ApartmentsEntity> httpEntity = new HttpEntity<>(apt, httpHeaders);
        ResponseEntity<String> response = template.exchange(base.toString() + "apartment/save/", HttpMethod.POST, httpEntity, String.class);
        System.out.println(response.getBody());
        assertThat(response.getBody(), equalTo(Constants.SUCCESS));
    }
    
    @Test
    public void testGet() throws Exception {
    	HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
    	ResponseEntity<ApartmentsEntity> response = template.exchange(base.toString() + "apartment/get/2", HttpMethod.GET, httpEntity, ApartmentsEntity.class);
    	ApartmentsEntity apt = response.getBody();
    	assertNotNull(apt);
    	assertEquals(apt.getAptName(), "Shilpitha Splendour Annex");
    }
}
