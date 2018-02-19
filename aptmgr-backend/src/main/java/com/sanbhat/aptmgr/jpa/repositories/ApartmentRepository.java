package com.sanbhat.aptmgr.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sanbhat.aptmgr.entities.ApartmentsEntity;

public interface ApartmentRepository extends CrudRepository<ApartmentsEntity, Integer> {

	
}
