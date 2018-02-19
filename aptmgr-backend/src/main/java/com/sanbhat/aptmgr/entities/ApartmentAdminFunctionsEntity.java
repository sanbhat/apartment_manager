package com.sanbhat.aptmgr.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ApartmentAdminFunctionsEntity implements Serializable {
	
	private static final long serialVersionUID = -7350451176018407178L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="apt_user_data_id")
	private int aptUserDataId;
	
	@Column(name="function_id")
	private int functionId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAptUserDataId() {
		return aptUserDataId;
	}

	public void setAptUserDataId(int aptUserDataId) {
		this.aptUserDataId = aptUserDataId;
	}

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	
}
