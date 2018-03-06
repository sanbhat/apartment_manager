package com.sanbhat.aptmgr.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.exceptions.UserException;
import com.sanbhat.aptmgr.jpa.repositories.UserRepository;

@Service
public class UserService {
	
	public enum ReturnCode {
		USER_EXISTS, SIGNUP_SUCCESSFUL, SIGNUP_FAILED, SUCCESS, FAILED
	}

	@Autowired
	private UserRepository userRepository;
	
	public ReturnCode signUpUser(String email, String name, String password) {
		UserEntity existing  = getUserByEmail(email);
		if(existing == null) {
			UserEntity userEntity = new UserEntity();
			userEntity.setEmail(email);
			userEntity.setName(name);
			userEntity.setPassword(password);
			UserEntity saved = userRepository.save(userEntity);
			return saved.getId() > 0 ? ReturnCode.SIGNUP_SUCCESSFUL : ReturnCode.SIGNUP_FAILED;
		} else {
			return ReturnCode.USER_EXISTS;
		}
	}
	
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public boolean validateUser(String email, String password) throws UserException {
		if(StringUtils.isEmpty(email)) {
			throw new IllegalArgumentException("Email value cannot be null");
		}
		UserEntity user = getUserByEmail(email);
		if(user == null) {
			throw new UserException("User record not found for email - "+email);
		}
		return user.getEmail().equals(email) && user.getPassword().equals(password);
	}
	
	public void updateUser(UserEntity user, int id) {
		UserEntity existing = userRepository.findOne(id);
		existing.setCountryCode(user.getCountryCode());
		existing.setPhone(user.getPhone());
		existing.setPictureUrl(user.getPictureUrl());
		userRepository.save(existing);
	}
}
