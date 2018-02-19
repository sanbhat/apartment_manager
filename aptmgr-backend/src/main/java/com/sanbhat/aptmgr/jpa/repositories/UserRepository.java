package com.sanbhat.aptmgr.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanbhat.aptmgr.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByEmail(String email);
}
