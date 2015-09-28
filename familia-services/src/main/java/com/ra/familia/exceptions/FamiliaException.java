package com.ra.familia.exceptions;

public class FamiliaException extends Exception{

	private Exception exception;

	public FamiliaException() {

	}
	
	public FamiliaException(Exception exception) {
		this.setException(exception);
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
