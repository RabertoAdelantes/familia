package com.ra.familia.entities;

public class PersonBean {
	private String ID; 
	private String first_name;
	private String midle_name;
	private String second_name;
	private String password_name;
	private String date_birth;
	private String date_death;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMidle_name() {
		return midle_name;
	}

	public void setMidle_name(String midle_name) {
		this.midle_name = midle_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getPassword_name() {
		return password_name;
	}

	public void setPassword_name(String password_name) {
		this.password_name = password_name;
	}

	public String getDate_birth() {
		return date_birth;
	}

	public void setDate_birth(String date_birth) {
		this.date_birth = date_birth;
	}

	public String getDate_death() {
		return date_death;
	}

	public void setDate_death(String date_death) {
		this.date_death = date_death;
	}
	@Override
	public String toString() {
		return first_name + " / "+midle_name+" / "+second_name+" * ";
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
