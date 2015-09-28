package com.ra.familia.entities;

public class PersonBean implements SuperBean{

	private static final long serialVersionUID = -7002784234984756018L;
	
	private String id;
	private String firstName;
	private String midleName;
	private String lastName;
	private String lastName2;
	private String password;
	private String dateBirth;
	private String dateDeath;
	private String email;
	private Object pathToFile;
	private byte[] dbFile;
	private boolean isDeleted;
	private boolean isActive;
	private String groupId;
	private String confirmationUuid;
	
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
		return firstName + " / " + midleName + " / " + lastName + " / " + dateDeath + "/" + dateBirth;
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

	public boolean getIsActive() {
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

	public Object getFilePath() {
		return pathToFile;
	}

	public void setFilePath(Object file) {
		this.pathToFile = file;
	}

	public byte[] getDbFile() {
		return dbFile;
	}

	public void setDbFile(byte[] dbFile) {
		this.dbFile = dbFile;
	}

	@Override
	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getConfirmationUuid() {
		return confirmationUuid;
	}

	public void setConfirmationUuid(String confirmationUuid) {
		this.confirmationUuid = confirmationUuid;
	}

}
