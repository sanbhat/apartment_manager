package com.sanbhat.aptmgr.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ApartmentUserDataEntity implements Serializable {

	private static final long serialVersionUID = 9100384934229202057L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="apt_id")
	private int aptId;
	
	@Column(name="apt_role_id")
	private int aptRoleId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAptId() {
		return aptId;
	}

	public void setAptId(int aptId) {
		this.aptId = aptId;
	}

	public int getAptRoleId() {
		return aptRoleId;
	}

	public void setAptRoleId(int aptRoleId) {
		this.aptRoleId = aptRoleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
