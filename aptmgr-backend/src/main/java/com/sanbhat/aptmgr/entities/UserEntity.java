package com.sanbhat.aptmgr.entities;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sanbhat.aptmgr.models.User;

@Entity
@Table(name="users")
@NamedQuery(name = "UserEntity.searchByNameOrEmail",
query="select u from UserEntity u where LOWER(u.name) like LOWER(?1) or LOWER(u.email) like LOWER(?1)")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -2584522971197739983L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String email;
	
	@Column
	private String name;
	
	@Column
	private String password;
	
	@Column(name="country_code")
	private int countryCode;
	
	@Column
	private String phone;
	
	@Column(name="picture_url")
	private String pictureUrl;
	
	@Column
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Transient
	public User toModel() {
		User user = new User();
		user.setId(this.id);
		user.setEmail(this.email);
		user.setName(this.name);
		user.setDisplayName(this.name + " ("+this.email+")");
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email=" + email + ", role= "+ role +", name=" +name+ ", password=" + password + ", countryCode=" + countryCode
				+ ", phone=" + phone + ", pictureUrl=" + pictureUrl + "]";
	}
	
}
