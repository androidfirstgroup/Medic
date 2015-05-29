
package com.medicine.ima.jsontasks;

// <copyright file="GetFormProfileTask.java" company="private">
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
import com.medicine.ima.IProfile;
import com.medicine.ima.ProfileMap;
import com.medicine.ima.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class GetFormProfileTask extends AsyncTask<Void, Void, JSONObject>{

			private static final String TAG_DATA = "Data";
			private static final String TAG_ERROR_MESSAGE = "ErrorMessage";

			String PROFILE_URL_GREEN = "/doctorcommunityprofile?profileId=%d&name=%s&isMain=%s";
			String PROFILE_URL_BLUE = "/doctorhospitalprofile?profileId=%d&name=%s&isMain=%s";

			private String url;
			private String errorMessage;
			private ProfileMap profileMap = new ProfileMap();

			IProfile profileInterface;
			private String cookies;
			private int profileId;

			private ProgressDialog pDialog;
			private int form;
			private String name;
			

			public GetFormProfileTask(IProfile profileInterface, String cookies, int profileId, String name, int form, boolean isMain){
				super();
				this.profileId = profileId;
				this.profileInterface = profileInterface;
				this.cookies = cookies;
				this.form = form;
				this.name = name;
				if (form == Constants.FORM_GREEN) {
					url = ((Context)profileInterface).getResources().getString(R.string.BASE_URL) + String.format(PROFILE_URL_GREEN, profileId, name, isMain ? "true" : "false");
				}
				else if(form == Constants.FORM_BLUE) {
					url = ((Context)profileInterface).getResources().getString(R.string.BASE_URL) + String.format(PROFILE_URL_BLUE, profileId, name, isMain ? "true" : "false");
				}
			}

			
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
			    		JSONObject data = json.getJSONObject(TAG_DATA);
			    		// System.out.println(data.toString());
			    		for (String key : profileMap.getProfileParams(form).keySet()) {
				    		String value = data.getString(key);
				    		System.out.println("key=" + key + " : Value=" + value);
				    		profileMap.setValue(key, value, form);
			    		}
			    	}
			   } catch (JSONException e) {
			     e.printStackTrace();
			   }
			    profileInterface.onProfileLoad(profileMap);
			}
}
