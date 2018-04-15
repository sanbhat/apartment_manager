package com.sanbhat.aptmgr.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="apartment_user_data")
@ToString
@EqualsAndHashCode(of={"user", "role"} )
public class ApartmentUserDataEntity implements Serializable {

	private static final long serialVersionUID = 9100384934229202057L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name="apt_role_id")
	private ApartmentRolesMetaEntity role;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="apt_id")
	private ApartmentsEntity apartment;
	
	@Column
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ApartmentRolesMetaEntity getRole() {
		return role;
	}

	public void setRole(ApartmentRolesMetaEntity role) {
		this.role = role;
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

	public ApartmentsEntity getApartment() {
		return apartment;
	}

	public void setApartment(ApartmentsEntity apartment) {
		this.apartment = apartment;
	}

}
