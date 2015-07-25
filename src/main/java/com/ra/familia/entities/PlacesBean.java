package com.ra.familia.entities;

import java.io.Serializable;
import java.util.List;

public class PlacesBean implements SuperBean, Serializable{

	private static final long serialVersionUID = 1304323181020065179L;
	private String ID; 
	private String name;
	private String notes;
	private List<TypesBean> types;
	private List<PersonBean> persons;

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TypesBean> getTypes() {
		return types;
	}

	public void setTypes(List<TypesBean> types) {
		this.types = types;
	}

	public List<PersonBean> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonBean> persons) {
		this.persons = persons;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
