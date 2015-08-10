package com.ra.familia.exceptions;

public class DaoExeception extends Exception {

	private final String localizedMessage;
	public DaoExeception(String localizedMessage) {
		this.localizedMessage = localizedMessage;
	}

	@Override
	public String getLocalizedMessage() {
		return localizedMessage;
	}

	private static final long serialVersionUID = -5950881434220124271L;
}
