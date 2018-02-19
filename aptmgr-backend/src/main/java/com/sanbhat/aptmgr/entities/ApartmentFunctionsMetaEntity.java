package com.sanbhat.aptmgr.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ApartmentFunctionsMetaEntity implements Serializable {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 5500767527569289629L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="function_key")
	private String functionKey;
	
	@Column(name="function_name")
	private String functionName;
	
	@Column
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFunctionKey() {
		return functionKey;
	}

	public void setFunctionKey(String functionKey) {
		this.functionKey = functionKey;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		ApartmentFunctionsMetaEntity other = (ApartmentFunctionsMetaEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApartmentFunctionsMetaEntity [id=" + id + ", functionKey=" + functionKey + ", functionName="
				+ functionName + ", description=" + description + "]";
	}
	
	
}
