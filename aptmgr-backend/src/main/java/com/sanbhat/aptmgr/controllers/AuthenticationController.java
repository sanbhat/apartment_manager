package com.sanbhat.aptmgr.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sanbhat.aptmgr.Constants;
import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.exceptions.UserException;
import com.sanbhat.aptmgr.models.LoginRequest;
import com.sanbhat.aptmgr.models.LoginResponse;
import com.sanbhat.aptmgr.models.Response;
import com.sanbhat.aptmgr.services.UserService;
import com.sanbhat.aptmgr.services.UserService.ReturnCode;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value="/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws UserException {
        boolean valid = userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword());
        
        if(valid) {
        	LoginResponse response = new LoginResponse();
        	String token = Jwts.builder()
	        	.setSubject(loginRequest.getEmail())
	            .claim(Constants.HTTP_ATTRIBUTE_LOGIN_NAME, loginRequest.getEmail())
	            .setIssuedAt(new Date())
	            .signWith(SignatureAlgorithm.HS256, Constants.JWT_KEY)
	            .compact();
        	response.setToken(token);
        	return response;
        } else {
        	throw new UserException("Invalid email or password.");
        }
    }
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public Response<String> signUpUser(@RequestBody UserEntity user) {
		ReturnCode ret =  userService.signUpUser(user);
		Response<String> response = new Response<>();
		switch(ret) {
			case USER_EXISTS:
				response.setErrorCode(ret.toString());
				response.setErrorMessage("User with email - " + user.getEmail() + " - is already registered");
				break;
			case SIGNUP_FAILED:
				response.setErrorCode(ret.toString());
				response.setErrorMessage("Sign up failed due to unknown reason");
				break;
			default:
				response.setMessage(ret.toString());
				break;
		}
		return response;
	}
}
