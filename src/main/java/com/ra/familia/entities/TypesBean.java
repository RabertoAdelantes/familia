package com.ra.familia.entities;

import java.io.Serializable;

public class TypesBean implements SuperBean, Serializable{
	private static final long serialVersionUID = -5155191490217284346L;
	private String ID; 
	private String name;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	@Override
	public String toString() {
		return name + " / "+type +" * ";
	}
}
