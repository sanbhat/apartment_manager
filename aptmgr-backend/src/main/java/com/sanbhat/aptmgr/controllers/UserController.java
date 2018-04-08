package com.sanbhat.aptmgr.controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanbhat.aptmgr.Constants;
import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.models.Response;
import com.sanbhat.aptmgr.models.UserSearchResponse;
import com.sanbhat.aptmgr.services.UserService;
import com.sanbhat.aptmgr.services.UserService.ReturnCode;

@RestController
@RequestMapping(value="/api/users")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/email", method = RequestMethod.PUT)
	public Response<String> updateUser(@RequestBody UserEntity user) {
		Response<String> response = new Response<>();
		try {
			userService.updateUser(user, user.getEmail());
		} catch(Exception e) {
			logger.error("UserController:updateUser => Error updating user -" +user.getEmail(), e);
			response.setErrorMessage(messageSource.getMessage("PROFILE_UPDATE_FAILED",  null, Locale.getDefault()));
			response.setErrorCode(ReturnCode.FAILED.toString());
			return response;
		}
		response.setMessage(messageSource.getMessage("PROFILE_UPDATE_SUCCESS",  null, Locale.getDefault()));
		return response; 
	}
	
	@RequestMapping(value="/email", method = RequestMethod.GET)
	public UserEntity getUserByEmail(@RequestParam(name="email") String email) {
		return userService.getUserByEmail(email);
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public Response<List<UserSearchResponse>> searchUser(@RequestParam(name="query") String query) {
		//TODO: Use Apache Solr?
		Response<List<UserSearchResponse>> result = new Response<>();
		try {
			List<UserSearchResponse> users = userService.searchByQuery(query);
			result.setPayload(users);
		} catch(Exception e) {
			logger.error("Exception performing search query on users", e);
			result.setErrorCode(Constants.FAILED);
			result.setErrorMessage(messageSource.getMessage("GENERIC_FAILURE", null, Locale.getDefault()));
		}
		return result;
	}
}
