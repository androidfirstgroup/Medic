package com.medicine.ima.jsontasks;

// <copyright file="GetAutorizationTask.java" company="private">
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

import java.security.Timestamp;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.medicine.ima.IAutorization;
import com.medicine.ima.R;
import com.medicine.ima.R.string;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.method.DateTimeKeyListener;
import android.widget.ListAdapter;

public class GetAutorizationTask extends AsyncTask<Void, Void, JSONObject>{

	private static final String TAG_DATA = "Data";
	private static final String TAG_ERROR_MESSAGE = "ErrorMessage";
	private static final String TAG_TOKEN = "Token";

	private String AUTORIZATION_URL = "doctor?login=%s&password=%s&dateBirth=%d&isNewVersion=true";
	private String url;

	private IAutorization productsInterface;


	public GetAutorizationTask(IAutorization productsInterface, String login, String pass, long birht){
		super();
		this.productsInterface = productsInterface;
		url = ((Context)productsInterface).getResources().getString(R.string.BASE_URL) + String.format(AUTORIZATION_URL, login, pass, birht);
	}
	
	private ProgressDialog pDialog;
    @Override
      protected void onPreExecute() {
          super.onPreExecute();
          pDialog = new ProgressDialog((Context)productsInterface);
          pDialog.setMessage("Getting Data ...");
          pDialog.setIndeterminate(false);
          pDialog.setCancelable(true);
          pDialog.show();
    }

	@Override
	protected JSONObject doInBackground(Void... arg0) {
		JSONParser jParser = new JSONParser(null);
		// Getting JSON from URL
		JSONObject json = jParser.getJSONFromUrl(url);
		return json;
	}
	
	String autorisationData;
	String errorMessage;

	@Override
    protected void onPostExecute(JSONObject json) {
	    pDialog.dismiss();
	    try {
	    	errorMessage = json.getString(TAG_ERROR_MESSAGE);
	    	if("".equals(json.getString(TAG_ERROR_MESSAGE))){
	    		JSONObject jsonData = json.getJSONObject(TAG_DATA);
	    		autorisationData = jsonData.getString(TAG_TOKEN);
	    	}
	   } catch (JSONException e) {
	     e.printStackTrace();
	   }
	    productsInterface.onAutorization(autorisationData);
	}

	
}
