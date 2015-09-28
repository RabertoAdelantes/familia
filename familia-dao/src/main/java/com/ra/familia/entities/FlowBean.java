package com.ra.familia.entities;

public class FlowBean implements SuperBean{

	private static final long serialVersionUID = 7765178853588282439L;
	
	private String id;
	private PersonBean pearsonBean;
	private MediaBean mediaBean;
	
	@Override
	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public PersonBean getPearsonBean() {
		return pearsonBean;
	}

	public void setPearsonBean(PersonBean pearsonBean) {
		this.pearsonBean = pearsonBean;
	}

	public MediaBean getMediaBean() {
		return mediaBean;
	}

	public void setMediaBean(MediaBean mediaBean) {
		this.mediaBean = mediaBean;
	}

	
	
}
