package com.softwarecenter.domain;

import java.io.Serializable;

public class DTO_Output extends DTO_Input implements Serializable {
	private static final long serialVersionUID = 1L;
	private int errorCode;

	public DTO_Output() {
		this.errorCode = 1001;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}