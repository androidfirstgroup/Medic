package com.medicine.ima.jsontasks;

// <copyright file="GetAllProfilesTask.java" company="private">
// Copyright (c) 2014 All Right Reserved, Andreev D.V.
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Andreev D.V.</author>
// <email></email>
// <date>2014-10-22</date>
// <summary>Medic organization profile</summary>


import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.medicine.ima.DoctorProfile;
import com.medicine.ima.IDoctorProfiles;
import com.medicine.ima.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class GetAllProfilesTask extends AsyncTask<Void, Void, JSONObject>{

	private static final String TAG_DATA = "Data";
	private static final String TAG_ERROR_MESSAGE = "ErrorMessage";
	private static final String TAG_ID = "Id";  // {"Id":118,"Name":"יפית2","IsDeleted":false,"IsMain":false}
	private static final String TAG_NAME = "Name";
	private static final String TAG_ISDELETED = "IsDeleted";
	private static final String TAG_ISMAIN = "IsMain";
	String PROFILE_URL = "/doctorprofiles?applicationType=%d";
	// {1-simple 2-community}

	private String url;
	private String errorMessage;
	private ArrayList<DoctorProfile> doctorProfiles = new ArrayList<DoctorProfile>();

	private IDoctorProfiles profileInterface;
	private String cookies;


	public GetAllProfilesTask(IDoctorProfiles profileInterface, String cookies, int applicationType){
		super();
		this.profileInterface = profileInterface;
		this.cookies = cookies;
		url = ((Context)profileInterface).getResources().getString(R.string.BASE_URL) + String.format(PROFILE_URL, applicationType);
	}
	
	private ProgressDialog pDialog;
    @Override
      protected void onPreExecute() {
          super.onPreExecute();
          pDialog = new ProgressDialog((Context)profileInterface);
          pDialog.setMessage("Getting Data ...");
          pDialog.setIndeterminate(false);
          pDialog.setCancelable(true);
          pDialog.show();
    }

	@Override
	protected JSONObject doInBackground(Void... arg0) {
		JSONParser jParser = new JSONParser(cookies);
		// Getting JSON from URL
		JSONObject json = jParser.getJSONFromUrl(url);
		return json;
	}
	
	
	
	@Override
    protected void onPostExecute(JSONObject json) {
	    pDialog.dismiss();
	    try {
	    	errorMessage = json.getString(TAG_ERROR_MESSAGE);
	    	if("".equals(errorMessage)){
		       JSONArray jData = json.getJSONArray(TAG_DATA);
			       for(int i=0; i < jData.length(); i ++ ){
			    	   JSONObject jItem = jData.getJSONObject(i);
			    	   int id = jItem.getInt(TAG_ID);
			    	   String name = jItem.getString(TAG_NAME);
			    	   boolean isDeleted = jItem.getBoolean(TAG_ISDELETED);
			    	   boolean isMain = jItem.getBoolean(TAG_ISMAIN);
			    	   doctorProfiles.add(new DoctorProfile(id, name, isDeleted ,isMain));
			       }
	    	}
	   } catch (JSONException e) {
	     e.printStackTrace();
	   }
	    profileInterface.onProfilesLoad(doctorProfiles);
	}

	
}
