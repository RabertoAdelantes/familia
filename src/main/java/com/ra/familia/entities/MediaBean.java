package com.ra.familia.entities;

import java.util.List;

public class MediaBean implements SuperBean{
	private static final long serialVersionUID = -6978842748315770797L;
	private String ID;
	private String source;
	private String notes;
	private TypeBean type;
	private List<PersonBean> persons;

	@Override
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

	public TypeBean getType() {
		return type;
	}

	public void setTypes(TypeBean type) {
		this.type = type;
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
