package com.sanbhat.aptmgr.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.exceptions.UserException;
import com.sanbhat.aptmgr.jpa.repositories.UserRepository;
import com.sanbhat.aptmgr.models.UserSearchResponse;
import com.sanbhat.aptmgr.models.ValidateUserResponse;

@Service
public class UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public enum ReturnCode {
		USER_EXISTS, SIGNUP_SUCCESSFUL, SIGNUP_FAILED, SUCCESS, FAILED
	}

	@Autowired
	private UserRepository userRepository;
	
	public ReturnCode signUpUser(UserEntity user) {
		UserEntity existing  = getUserByEmail(user.getEmail());
		logger.info("UserService:singUpUser => signing up user - " + user.getEmail());
		if(existing == null) {
			UserEntity userEntity = new UserEntity();
			userEntity.setEmail(user.getEmail());
			userEntity.setName(user.getName());
			userEntity.setPassword(user.getPassword());
			userEntity.setCountryCode(user.getCountryCode());
			userEntity.setPhone(user.getPhone());
			userEntity.setPictureUrl(user.getPictureUrl());
			UserEntity saved = userRepository.save(userEntity);
			return saved.getId() > 0 ? ReturnCode.SIGNUP_SUCCESSFUL : ReturnCode.SIGNUP_FAILED;
		} else {
			return ReturnCode.USER_EXISTS;
		}
	}
	
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public ValidateUserResponse validateUser(String email, String password) throws UserException {
		ValidateUserResponse response = new ValidateUserResponse();
		if(StringUtils.isEmpty(email)) {
			throw new IllegalArgumentException("Email value cannot be null");
		}
		UserEntity user = getUserByEmail(email);
		if(user == null) {
			throw new UserException("User record not found for email - "+email);
		}
		boolean isValid = user.getEmail().equals(email) && user.getPassword().equals(password);
		response.setValid(isValid);
		if(isValid) {
			response.setUserEntity(user);
		}
		return response;
 	}
	
	public void updateUser(UserEntity user, String email) {
		UserEntity existing = userRepository.findByEmail(email);
		existing.setName(user.getName());
		existing.setCountryCode(user.getCountryCode());
		existing.setPhone(user.getPhone());
		existing.setPictureUrl(user.getPictureUrl());
		userRepository.save(existing);
	}
	
	public List<UserSearchResponse> searchByQuery(String query) {
		String modifiedQuery = "%" + query.toLowerCase() +"%";
		List<UserSearchResponse> result = new ArrayList<>();
		List<UserEntity> users =  userRepository.searchByNameOrEmail(modifiedQuery);
		if(CollectionUtils.isNotEmpty(users)) {
			for(UserEntity u : users) {
				UserSearchResponse r = new UserSearchResponse();
				r.setEmail(u.getEmail());
				r.setDisplayValue(u.getName() + " ("+u.getEmail()+")");
				result.add(r);
			}
		}
		return result;
	}
}
