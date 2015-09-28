package com.ra.familia.entities;

public class MediaRefBean implements SuperBean{
	private static final long serialVersionUID = -6978842748315770797L;
	
	private String ID;
	private String person_fk;
	private String media_fk;

	@Override
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPerson_fk() {
		return person_fk;
	}

	public void setPerson_fk(String person_fk) {
		this.person_fk = person_fk;
	}

	public String getMedia_fk() {
		return media_fk;
	}

	public void setMedia_fk(String media_fk) {
		this.media_fk = media_fk;
	}

	

}
