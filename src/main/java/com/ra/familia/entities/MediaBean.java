package com.ra.familia.entities;

import java.io.Serializable;
import java.util.List;

public class MediaBean implements SuperBean, Serializable{
	private static final long serialVersionUID = -6978842748315770797L;
	private String ID;
	private String source;
	private String notes;
	private List<TypesBean> types;
	private List<PersonBean> persons;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	} 
}
