package com.sanbhat.aptmgr.controllers;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.sanbhat.aptmgr.models.ValidateUserResponse;
import com.sanbhat.aptmgr.services.UserService;
import com.sanbhat.aptmgr.services.UserService.ReturnCode;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value="/api/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws UserException {
        ValidateUserResponse validateUserResponse = userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword());
        
        if(validateUserResponse.isValid()) {
        	LoginResponse response = new LoginResponse();
        	String token = Jwts.builder()
	        	.setSubject(loginRequest.getEmail())
	            .claim(Constants.CLAIM_LOGIN_NAME, validateUserResponse.getUserEntity().getEmail())
	            .claim(Constants.CLAIM_USER_ROLE, validateUserResponse.getUserEntity().getRole())
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
				response.setErrorMessage(messageSource.getMessage(ret.toString(), new Object[]{user.getEmail()}, Locale.getDefault()));
				break;
			case SIGNUP_FAILED:
				response.setErrorCode(ret.toString());
				response.setErrorMessage(messageSource.getMessage(ret.toString(), null, Locale.getDefault()));
				break;
			default:
				response.setMessage(messageSource.getMessage(ret.toString(), null, Locale.getDefault()));
				break;
		}
		return response;
	}
}
