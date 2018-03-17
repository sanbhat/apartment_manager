package com.sanbhat.aptmgr.models;

import lombok.Data;

@Data
public class Response<T> {
	
	private String errorCode;
	
	private String errorMessage;
	
	private String message;
	
	private T payload;
	
	public boolean hasError() {
		return errorCode != null || errorMessage != null;
	}
}
