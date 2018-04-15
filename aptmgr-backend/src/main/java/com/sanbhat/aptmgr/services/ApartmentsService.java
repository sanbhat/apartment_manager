package com.sanbhat.aptmgr.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanbhat.aptmgr.entities.ApartmentRolesMetaEntity;
import com.sanbhat.aptmgr.entities.ApartmentUserDataEntity;
import com.sanbhat.aptmgr.entities.ApartmentsEntity;
import com.sanbhat.aptmgr.entities.UserEntity;
import com.sanbhat.aptmgr.jpa.repositories.ApartmentRepository;
import com.sanbhat.aptmgr.jpa.repositories.ApartmentRolesRepository;
import com.sanbhat.aptmgr.jpa.repositories.UserRepository;
import com.sanbhat.aptmgr.models.Apartment;
import com.sanbhat.aptmgr.models.ApartmentRole;
import com.sanbhat.aptmgr.models.ApartmentUserRoleMapping;

@Service
public class ApartmentsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ApartmentRepository apartmentsRepository;
	
	@Autowired
	private ApartmentRolesRepository apartmentRolesRepository;
	
	
	public boolean createApartment(Apartment apt) {
		ApartmentsEntity saved = apartmentsRepository.save(apt.toEntity());
		return saved.getId() > 0;
	}
	
	public void updateApartment(Apartment apt) {
		ApartmentsEntity aptEntity = apartmentsRepository.findOne(apt.getId());
		aptEntity.setPrimaryEmail(apt.getPrimaryEmail());
		aptEntity.setAddress(apt.getAddress());
		aptEntity.setCity(apt.getCity());
		aptEntity.setPinCode(apt.getPinCode());
		
		updateApartmentRoleMappings(apt, aptEntity);
		apartmentsRepository.save(aptEntity);
	}

	private void updateApartmentRoleMappings(Apartment aptModel, ApartmentsEntity aptEntity) {
		Set<ApartmentUserDataEntity> aptUserDataList = aptEntity.getApartmentUsers();
		
		for(ApartmentUserRoleMapping roleMappingModel : aptModel.getApartmentRoleMappings()) {
			if(roleMappingModel.getUser() != null) {
				ApartmentRolesMetaEntity role = apartmentRolesRepository.getOne(roleMappingModel.getRole().getRoleId());
				UserEntity user = userRepository.getOne(roleMappingModel.getUser().getId());
				
				ApartmentUserDataEntity oldUserData = findExistingAptUserDataEntityForRole(aptUserDataList, roleMappingModel.getRole().getRoleId());
				
				if(oldUserData == null) {
					ApartmentUserDataEntity newUserData = new ApartmentUserDataEntity();
					newUserData.setRole(role);
					newUserData.setUser(user);
					newUserData.setApartment(aptEntity);
					aptUserDataList.add(newUserData);
				} else if(!user.equals(oldUserData.getUser())){
					oldUserData.setUser(user);
				}
			}
		}
	}
	
	
	private ApartmentUserDataEntity findExistingAptUserDataEntityForRole(Set<ApartmentUserDataEntity> aptUserDataList,
			int roleId) {
		ApartmentUserDataEntity result = null;
		if(CollectionUtils.isNotEmpty(aptUserDataList)) {
			for(ApartmentUserDataEntity a : aptUserDataList) {
				if(a.getRole().getId() == roleId) {
					result = a;
					break;
				}
			}
		}
		return result;
	}

	public Apartment getApartmentById(int id) {
		List<ApartmentRolesMetaEntity> allRoles = apartmentRolesRepository.findAllCoreRoles();
		ApartmentsEntity apartmentEntity = apartmentsRepository.findOne(id);
		Apartment apartment = apartmentEntity.toModel();
		
		List<ApartmentUserRoleMapping> aptUserRoleMappings = new ArrayList<>();
		for(ApartmentRolesMetaEntity metaRoleEntity : allRoles) {
			ApartmentUserRoleMapping aptUserRoleMapping = new ApartmentUserRoleMapping();
			aptUserRoleMapping.setApartmentId(apartment.getId());
			
			ApartmentRole role = new ApartmentRole();
			role.setRoleId(metaRoleEntity.getId());
			role.setRoleName(metaRoleEntity.getRole());
			role.setDescription(metaRoleEntity.getDescription());
			aptUserRoleMapping.setRole(role);
			
			if(CollectionUtils.isNotEmpty(apartmentEntity.getApartmentUsers())) {
				for(ApartmentUserDataEntity apartmentUserDataEntity : apartmentEntity.getApartmentUsers()) {
					if(apartmentUserDataEntity.getRole().getId() == metaRoleEntity.getId()) {
						aptUserRoleMapping.setUser(apartmentUserDataEntity.getUser().toModel());
						aptUserRoleMapping.setId(apartmentUserDataEntity.getId());
					}
				}
			}
			
			aptUserRoleMappings.add(aptUserRoleMapping);
		}
		apartment.setApartmentRoleMappings(aptUserRoleMappings);
		return apartment;
	}

	public List<Apartment> getAllApartments() {
		Iterable<ApartmentsEntity> list = apartmentsRepository.findAll();
		List<Apartment> result = new ArrayList<>();
		for(ApartmentsEntity a : list) {
			result.add(a.toModel());
		}
		return result;
	}
}
