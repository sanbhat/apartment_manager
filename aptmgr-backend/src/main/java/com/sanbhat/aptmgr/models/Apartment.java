package com.sanbhat.aptmgr.models;

import java.util.List;

import com.sanbhat.aptmgr.entities.ApartmentsEntity;

import lombok.Data;

@Data
public class Apartment {
	
	private int id;
	
	private String aptName;
	
	private String primaryEmail;

	private String address;
	
	private String city;
			
	private String pinCode;
	
	private List<ApartmentUserRoleMapping> apartmentRoleMappings;
	
	public ApartmentsEntity toEntity() {
		ApartmentsEntity entity = new ApartmentsEntity();
		entity.setId(this.id);
		entity.setAptName(this.aptName);
		entity.setPrimaryEmail(this.primaryEmail);
		entity.setAddress(this.address);
		entity.setCity(this.city);
		entity.setPinCode(this.pinCode);
		return entity;
	}

}
