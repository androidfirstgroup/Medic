package com.medicine.ima;

public class DoctorProfile {

	int Id;
	String Name;
	boolean IsDeleted;
	boolean IsMain;

	public DoctorProfile(	int Id, String Name, boolean IsDeleted, boolean IsMain){
		this.Id = Id;
		this.Name = Name;
		this.IsDeleted = IsDeleted;
		this.IsMain = IsMain;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

}
