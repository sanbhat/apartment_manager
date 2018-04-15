package com.sanbhat.aptmgr.models;

import lombok.Data;

@Data
public class ApartmentUserRoleMapping {
	
	private int id;
	
	private int apartmentId;
	
	private ApartmentRole role;
	
	private User user;

}
