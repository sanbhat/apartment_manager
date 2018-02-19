package com.sanbhat.aptmgr.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ApartmentOccupancyEntity implements Serializable {

	private static final long serialVersionUID = 5976437863492525699L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="apt_unit_id")
	private int aptUnitId;
	
	@Column(name="userId")
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAptUnitId() {
		return aptUnitId;
	}

	public void setAptUnitId(int aptUnitId) {
		this.aptUnitId = aptUnitId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
