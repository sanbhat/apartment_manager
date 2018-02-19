package com.sanbhat.aptmgr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.services.UserService;
import com.sanbhat.aptmgr.services.UserService.ReturnCode;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUpUser(@RequestBody UserEntity user) {
		ReturnCode ret =  userService.signUpUser(user.getEmail(), user.getName(), user.getPassword());
		return ret.toString();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public String updateUser(@RequestBody UserEntity user, @PathVariable(name="id") int id) {
		try {
			userService.updateUser(user, id);
		} catch(Exception e) {
			//TODO log error
			return ReturnCode.FAILED.toString();
		}
		return ReturnCode.SUCCESS.toString(); 
	}
	
	@RequestMapping(value="/email")
	public UserEntity getUserByEmail(@RequestParam(name="email") String email) {
		return userService.getUserByEmail(email);
	}
}
