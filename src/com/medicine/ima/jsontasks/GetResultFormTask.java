
package com.medicine.ima.jsontasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.medicine.ima.Constants;
import com.medicine.ima.IProfile;
import com.medicine.ima.ProfileMap;
import com.medicine.ima.ProfileMap.ResultForm;
import com.medicine.ima.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class GetResultFormTask extends AsyncTask<Void, Void, JSONObject>{

			private static final String TAG_DATA = "Data";
			private static final String TAG_ERROR_MESSAGE = "ErrorMessage";

			String PROFILE_URL_GREEN = "/doctorcommunityprofileresult?profileId=%d&isMain=%s";	// One Form
			String PROFILE_URL_BLUE = "/doctorhospitalprofileresult?profileId=%d&isMain=%s";		//	Two Form

			private String url;
			private String errorMessage;
			private ProfileMap profileMap = new ProfileMap();

			IProfileResult profileResultInterface;
			private String cookies;
			private int profileId;

			private ProgressDialog pDialog;
			private int form;
			

			public GetResultFormTask(IProfileResult profileProfileInterface, String cookies, int profileId, int form, boolean isMain){
				super();
				this.profileId = profileId;
				this.profileResultInterface = profileProfileInterface;
				this.cookies = cookies;
				this.form = form;
				if (form == Constants.FORM_GREEN) {
					url = ((Context)profileProfileInterface).getResources().getString(R.string.BASE_URL) + String.format(PROFILE_URL_GREEN, profileId, isMain ? "true" : "false");
				}else if (form == Constants.FORM_BLUE) {
					url = ((Context)profileProfileInterface).getResources().getString(R.string.BASE_URL) + String.format(PROFILE_URL_BLUE, profileId, isMain ? "true" : "false");
				}
			}

		    @Override
		      protected void onPreExecute() {
		          super.onPreExecute();
		          pDialog = new ProgressDialog((Context)profileResultInterface);
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
			    ArrayList<ResultForm> resultOneForm = profileMap.getResultForm(form);

			    try {
			    	errorMessage = json.getString(TAG_ERROR_MESSAGE);
			    	if("".equals(errorMessage)){
			    		JSONArray dataGroup = json.getJSONArray(TAG_DATA);
			    		
			    		System.out.println(json.toString());
			    		
			    		for(int i=0; i< resultOneForm.size(); i++){
			    			JSONArray dataSections = dataGroup.getJSONArray(i);
			    			System.out.println("OneForm group:" + resultOneForm.get(i).title );
				    		for(int j=0; j< resultOneForm.get(i).sections.size(); j++){
				    			int type = getTypeResultValue(i,j);
				    			if(type == Constants.DOUBLE_TYPE){
				    				double valueDouble = dataSections.getDouble(j);
				    				if(form == Constants.FORM_GREEN && i==11 && j == 0){
				    					String str;
				    					 if (valueDouble != 0)
				    						 	str = "פריפריה";
				    						 else
				    							 str = "מרכז";
				    					 resultOneForm.get(i).sections.get(j).setValue(str);
				    				}else{
				    					resultOneForm.get(i).sections.get(j).setValue(valueDouble);
				    				}
				    			}if(type == Constants.STRING_TYPE){
				    				String valueString = dataSections.getString(j);
					    			resultOneForm.get(i).sections.get(j).setValue(valueString);
				    			}
				    			System.out.println("   OneForm child:" + resultOneForm.get(i).sections.get(j).title);
				    		}
			    		}
			    	}
			   } catch (JSONException e) {
			     e.printStackTrace();
			   }
			    profileResultInterface.onProfileResultLoad(resultOneForm);
			}

			
			private int getTypeResultValue(int i, int j) {
				if(form == Constants.FORM_GREEN){
					
					return Constants.DOUBLE_TYPE;
				}else if(form == Constants.FORM_BLUE){
					if(i == 14 && (j == 0 || j==1))
						return Constants.STRING_TYPE;
					else
						return Constants.DOUBLE_TYPE;
				}
				return Constants.ERR_TYPE;
			}
}

