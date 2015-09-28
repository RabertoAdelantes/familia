package com.ra.familia.entities;

import java.util.Date;

public class ConfirmationBean implements SuperBean{

	private static final long serialVersionUID = 7765178853588282439L;
	
	private String id;
	private Long userReference;
	private final String link;
	private Date expired;
	private boolean isUsed;
	
	
	public ConfirmationBean(String link, long personPk,Date expiredDate) {
		this.link = link;
		this.userReference = personPk;
		this.expired = expiredDate;
	}

	@Override
	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public Long getUserReference() {
		return userReference;
	}

	public void setUserReference(Long useerReference) {
		this.userReference = useerReference;
	}

	public String getLink() {
		return link;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
}
