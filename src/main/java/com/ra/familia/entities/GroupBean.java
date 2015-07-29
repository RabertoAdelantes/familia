package com.ra.familia.entities;


public class GroupBean implements SuperBean{

	private static final long serialVersionUID = -7002784234984756018L;
	
	private String id;
	private String name;
	
	public void setID(String id) {
		this.id = id;
	}
	@Override
	public String getID() {
		return this.id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Name:"+name+",ID"+id;
	}

}
