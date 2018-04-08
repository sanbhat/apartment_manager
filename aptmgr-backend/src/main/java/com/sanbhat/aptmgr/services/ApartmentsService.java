package com.sanbhat.aptmgr.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanbhat.aptmgr.entities.ApartmentRolesMetaEntity;
import com.sanbhat.aptmgr.entities.ApartmentUserDataEntity;
import com.sanbhat.aptmgr.entities.ApartmentsEntity;
import com.sanbhat.aptmgr.jpa.repositories.ApartmentRepository;
import com.sanbhat.aptmgr.jpa.repositories.ApartmentRolesRepository;
import com.sanbhat.aptmgr.models.Apartment;
import com.sanbhat.aptmgr.models.ApartmentRole;
import com.sanbhat.aptmgr.models.ApartmentUserRoleMapping;
import com.sanbhat.aptmgr.models.User;

@Service
public class ApartmentsService {

	@Autowired
	private ApartmentRepository apartmentsRepository;
	
	@Autowired
	private ApartmentRolesRepository apartmentRolesRepository;
	
	
	public boolean createApartment(ApartmentsEntity apt) {
		ApartmentsEntity saved = apartmentsRepository.save(apt);
		return saved.getId() > 0;
	}
	
	public Apartment getApartmentById(int id) {
		List<ApartmentRolesMetaEntity> allRoles = apartmentRolesRepository.findAll();
		ApartmentsEntity apartmentEntity = apartmentsRepository.findOne(id);
		Apartment apartment = new Apartment();
		int aptId = apartmentEntity.getId();
		apartment.setId(aptId);
		apartment.setName(apartmentEntity.getAptName());
		
		List<ApartmentUserRoleMapping> mappings = new ArrayList<>();
		for(ApartmentRolesMetaEntity metaRoleEntity : allRoles) {
			ApartmentUserRoleMapping apartmentUserData = new ApartmentUserRoleMapping();
			apartmentUserData.setApartmentId(aptId);
			
			ApartmentRole role = new ApartmentRole();
			role.setRoleId(metaRoleEntity.getId());
			role.setRoleName(metaRoleEntity.getRole());
			role.setDescription(metaRoleEntity.getDescription());
			apartmentUserData.setRole(role);
			
			if(CollectionUtils.isNotEmpty(apartmentEntity.getApartmentUsers())) {
				for(ApartmentUserDataEntity apartmentUserDataEntity : apartmentEntity.getApartmentUsers()) {
					if(apartmentUserDataEntity.getAptRoleId() == metaRoleEntity.getId()) {
						User user = new User();
						user.setEmail(apartmentUserDataEntity.getUser().getEmail());
						user.setId(apartmentUserData.getUser().getId());
						user.setName(apartmentUserData.getUser().getName());
						apartmentUserData.setUser(user);
					}
				}
			}
			
			mappings.add(apartmentUserData);
		}
		apartment.setApartmentRoleMappings(mappings);
		return apartment;
	}

	public List<ApartmentsEntity> getAllApartments() {
		Iterable<ApartmentsEntity> list = apartmentsRepository.findAll();
		List<ApartmentsEntity> result = new ArrayList<>();
		for(ApartmentsEntity a : list) {
			result.add(a);
		}
		return result;
	}
}
