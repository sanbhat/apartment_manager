package com.sanbhat.aptmgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sanbhat.aptmgr.Constants;
import com.sanbhat.aptmgr.entities.ApartmentsEntity;
import com.sanbhat.aptmgr.services.ApartmentsService;

@RestController
@RequestMapping(value="/apartment")
public class ApartmentController {
	
	@Autowired
	private ApartmentsService apartmentsService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveApartment(@RequestBody ApartmentsEntity apt) {
		if(apartmentsService.createApartment(apt)) {
			return Constants.SUCCESS;
		}
		return Constants.FAILED;
	}
	
	@RequestMapping(value="/get/{id}")
	public ApartmentsEntity getApartment(@PathVariable int id) {
		return apartmentsService.getApartmentById(id);
	}
}
