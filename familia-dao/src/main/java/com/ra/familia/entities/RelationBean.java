package com.ra.familia.entities;

public class RelationBean implements SuperBean {
	
	private static final long serialVersionUID = 1156916959296685521L;
	
	private String id;
	private String personId;
	private String personRelationId;
	private String typeId;

	@Override
	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getPersonRelationId() {
		return personRelationId;
	}

	public void setPersonRelationId(String personRelationId) {
		this.personRelationId = personRelationId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}
