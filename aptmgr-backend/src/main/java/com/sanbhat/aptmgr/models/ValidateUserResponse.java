package com.sanbhat.aptmgr.models;

import com.sanbhat.aptmgr.entities.UserEntity;

import lombok.Data;

@Data
public class ValidateUserResponse {

	private boolean isValid;
	
	private UserEntity userEntity;
}
