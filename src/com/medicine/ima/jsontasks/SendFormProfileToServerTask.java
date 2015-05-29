package com.medicine.ima.jsontasks;

// <copyright file="SendFormProfileToServerTask.java" company="private">
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

import org.json.JSONException;
import org.json.JSONObject;

import com.medicine.ima.Constants;
import com.medicine.ima.IProfilePOSTToServer;
import com.medicine.ima.ProfileMap;
import com.medicine.ima.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class SendFormProfileToServerTask extends AsyncTask<Void, Void, JSONObject>{

			private static final String TAG_DATA = "Data";
			private static final String TAG_ERROR_MESSAGE = "ErrorMessage";

			String PROFILE_URL_BLUE = "/doctorhospitalprofile?profileId=%d&name=%s&isMain=%s";
			String PROFILE_URL_GREEN = "/doctorcommunityprofile?profileId=%d&name=%s&isMain=%s";

			
			private String url;
			private String errorMessage;
			private ProfileMap profileMap = new ProfileMap();

			IProfilePOSTToServer profilePOSTServer;
			private String cookies;
			//private int profileId;

			private ProgressDialog pDialog;
			private int form;
			JSONObject dataJson;

			public SendFormProfileToServerTask(IProfilePOSTToServer profilePOSTServer, String cookies, int profileId, int form, String name, boolean isMain, JSONObject dataJson){
				super();
				//this.profileId = profileId;
				this.profilePOSTServer = profilePOSTServer;
				this.cookies = cookies;
				this.form = form;
				this.dataJson = dataJson;
				if ( form == Constants.FORM_GREEN ){
					url = ((Context)profilePOSTServer).getResources().getString(R.string.BASE_URL) + String.format(PROFILE_URL_GREEN, profileId, name, isMain ? "true" : "false");
				}else if (form == Constants.FORM_BLUE){
					url = ((Context)profilePOSTServer).getResources().getString(R.string.BASE_URL) + String.format(PROFILE_URL_BLUE, profileId, name, isMain ? "true" : "false");
				}
			}

			
		    @Override
		      protected void onPreExecute() {
		          super.onPreExecute();
		          pDialog = new ProgressDialog((Context)profilePOSTServer);
		          pDialog.setMessage("Getting Data ...");
		          pDialog.setIndeterminate(false);
		          pDialog.setCancelable(true);
		          pDialog.show();
		    }

			@Override
			protected JSONObject doInBackground(Void... arg0) {
				JSONPOSTParser jParser = new JSONPOSTParser(cookies);
				// Getting JSON from URL
				JSONObject json = jParser.sendJSONToServer(url, dataJson);
				return json;
			}
			

			// {"Data":{"Id":-1,"Name":null,"IsMain":true},"ErrorMessage":""}
			@Override
		    protected void onPostExecute(JSONObject json) {
			    pDialog.dismiss();
			    try {
			    	errorMessage = json.getString(TAG_ERROR_MESSAGE);
			    	if("".equals(errorMessage)){
			    		JSONObject data = json.getJSONObject(TAG_DATA);

			    	}
			   } catch (JSONException e) {
			     e.printStackTrace();
			   }
			    profilePOSTServer.onProfileSendServer(profileMap);
			}
}
