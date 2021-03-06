package com.sanbhat.aptmgr.controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sanbhat.aptmgr.Constants;
import com.sanbhat.aptmgr.models.Apartment;
import com.sanbhat.aptmgr.models.Response;
import com.sanbhat.aptmgr.services.ApartmentsService;

@RestController
@RequestMapping(value="/api/apartment")
public class ApartmentController {
	
	private static Logger logger = LoggerFactory.getLogger(ApartmentController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ApartmentsService apartmentsService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public Response<String> saveApartment(@RequestBody Apartment apt) {
		Response<String> response = new Response<>();
		try {
			if(apartmentsService.createApartment(apt)) {
				response.setMessage(messageSource.getMessage("APARTMENT_CREATED", null, Locale.getDefault())); 
			} else {
				logger.error("Error creating apartment data");
				response.setErrorCode(Constants.FAILED);
				response.setErrorMessage(messageSource.getMessage("GENERIC_FAILURE", null, Locale.getDefault()));
			}
		} catch(Exception e) {
			logger.error("Error creating apartment data", e);
			response.setErrorCode(Constants.FAILED);
			response.setErrorMessage(messageSource.getMessage("GENERIC_FAILURE", null, Locale.getDefault()));
		}
		return response;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public Response<String> updateApartment(@RequestBody Apartment apt) {
		Response<String> response = new Response<>();
		try {
			apartmentsService.updateApartment(apt);
			response.setMessage(messageSource.getMessage("APARTMENT_UPDATED", null, Locale.getDefault()));
		} catch(Exception e) {
			logger.error("Error updating apartment data", e);
			response.setErrorCode(Constants.FAILED);
			response.setErrorMessage(messageSource.getMessage("GENERIC_FAILURE", null, Locale.getDefault()));
		}
		return response;
	}
	
	@RequestMapping(value="/get/{id}")
	public Apartment getApartment(@PathVariable int id) {
		return apartmentsService.getApartmentById(id);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Apartment> getAllApartments() {
		return apartmentsService.getAllApartments();
	}
}
