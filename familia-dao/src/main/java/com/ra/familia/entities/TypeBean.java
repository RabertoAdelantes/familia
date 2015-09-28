package com.ra.familia.entities;

public class TypeBean implements SuperBean{
	
	private static final long serialVersionUID = -5155191490217284346L;
	private String ID; 
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
