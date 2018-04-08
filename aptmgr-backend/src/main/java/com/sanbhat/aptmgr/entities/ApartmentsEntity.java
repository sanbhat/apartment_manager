package com.sanbhat.aptmgr.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="apartments")
@Entity
public class ApartmentsEntity implements Serializable {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -8592116815125564309L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="apt_name")
	private String aptName;
	
	@Column(name="apt_primary_email")
	private String primaryEmail;

	@Column(name="apt_address")
	private String address;
	
	@Column(name="apt_city")
	private String city;
			
	@Column(name="apt_pincode")
	private String pinCode;

	@OneToMany
	@JoinColumn(name="id")
	private Set<ApartmentUserDataEntity> apartmentUsers;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	public Set<ApartmentUserDataEntity> getApartmentUsers() {
		return apartmentUsers;
	}

	public void setApartmentUsers(Set<ApartmentUserDataEntity> apartmentUsers) {
		this.apartmentUsers = apartmentUsers;
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
		ApartmentsEntity other = (ApartmentsEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApartmentsEntity [id=" + id + ", aptName=" + aptName + ", primaryEmail=" + primaryEmail + ", address="
				+ address + ", city=" + city + ", pinCode=" + pinCode + "]";
	}
	
}
