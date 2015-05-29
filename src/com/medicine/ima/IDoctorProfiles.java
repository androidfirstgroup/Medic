package com.medicine.ima;

import java.util.ArrayList;

public interface IDoctorProfiles {
	void onProfilesLoad(ArrayList<DoctorProfile> doctorProfiles);
	void onProfilesEdit(DoctorProfile doctorProfile);
	void onProfilesDelete(DoctorProfile doctorProfile);
}
