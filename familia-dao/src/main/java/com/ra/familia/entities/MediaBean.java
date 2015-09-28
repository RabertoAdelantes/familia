package com.ra.familia.entities;

import java.util.Date;

public class MediaBean implements SuperBean{
	private static final long serialVersionUID = -6978842748315770797L;
	private String ID;
	private byte[] source;
	private String notes;
	private TypeBean type;
	private String externalSrc;
	private boolean isPrimary;
	private Date date;

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

	public byte[] getSource() {
		return source;
	}

	public void setSource(byte[] source) {
		this.source = source;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExternalSrc() {
		return externalSrc;
	}

	public void setExternalSrc(String externalsSrc) {
		this.externalSrc = externalsSrc;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

}
