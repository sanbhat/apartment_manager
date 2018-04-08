package com.sanbhat.aptmgr.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="apartment_user_data")
public class ApartmentUserDataEntity implements Serializable {

	private static final long serialVersionUID = 9100384934229202057L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="apt_id")
	private int aptId;
	
	@Column(name="apt_role_id")
	private int aptRoleId;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
