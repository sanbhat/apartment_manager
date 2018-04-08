package com.sanbhat.aptmgr.models;

import java.util.List;

import lombok.Data;

@Data
public class Apartment {
	
	private int id;
	
	private String name;
	
	private List<ApartmentUserRoleMapping> apartmentRoleMappings;

}
