package com.sanbhat.aptmgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanbhat.aptmgr.entities.ApartmentsEntity;
import com.sanbhat.aptmgr.jpa.repositories.ApartmentRepository;

@Service
public class ApartmentsService {

	@Autowired
	private ApartmentRepository apartmentsRepository;
	
	public boolean createApartment(ApartmentsEntity apt) {
		ApartmentsEntity saved = apartmentsRepository.save(apt);
		return saved.getId() > 0;
	}
	
	public ApartmentsEntity getApartmentById(int id) {
		return apartmentsRepository.findOne(id);
	}
}
