package com.ra.familia.entities;

public class PersonBean {
	private String id;
	private String firstName;
	private String midleName;
	private String lastName;
	private String lastName2;
	private String password;
	private String dateBirth;
	private String dateDeath;
	private String email;
	private Object photo;
	private boolean isDeleted;
	private boolean isActive;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidleName() {
		return midleName;
	}

	public void setMidleName(String midleName) {
		this.midleName = midleName;
	}

	public String getSecondName() {
		return lastName;
	}

	public void setLastName(String secondName) {
		this.lastName = secondName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwordName) {
		this.password = passwordName;
	}

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getDateDeath() {
		return dateDeath;
	}

	public void setDateDeath(String dateDeath) {
		this.dateDeath = dateDeath;
	}

	@Override
	public String toString() {
		return firstName + " / " + midleName + " / " + lastName + " * ";
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public String setEmail(String email) {
		return this.email = email;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	public Object getPhoto() {
		return photo;
	}

	public void setPhoto(Object file) {
		this.photo = file;
	}

}
