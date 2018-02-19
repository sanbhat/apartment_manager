package com.sanbhat.aptmgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanbhat.aptmgr.entities.UserEntity;
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
	
	public void updateUser(UserEntity user, int id) {
		UserEntity existing = userRepository.findOne(id);
		existing.setCountryCode(user.getCountryCode());
		existing.setPhone(user.getPhone());
		existing.setPictureUrl(user.getPictureUrl());
		userRepository.save(existing);
	}
}
