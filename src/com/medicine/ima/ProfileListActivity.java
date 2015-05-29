package com.medicine.ima;

// <copyright file="ProfileListActivity.java" company="private">
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

import com.medicine.ima.jsontasks.GetAllProfilesTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;


public class ProfileListActivity extends Activity implements OnClickListener, IDoctorProfiles{
	private Context context;
	private ProfilesArrayAdapter profilesArrayAdapter;
	private String autorisationData;


	ListView lv;
	private int form;
	private ImageView popup_menu;
	private TextView btnBack;
	private int applicationType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_list_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		context = this;
		Bundle bundle = getIntent().getExtras();
		autorisationData = bundle.getString("autorisationData");
		form = bundle.getInt("form");

		popup_menu = (ImageView) findViewById(R.id.popup_menu);
 		popup_menu.setOnClickListener(this);
		btnBack = (TextView)findViewById(R.id.wrapper_header_backbutton_lbl);
		btnBack.setOnClickListener(this);

	
		ArrayList<DoctorProfile> val = new ArrayList<DoctorProfile>();
		profilesArrayAdapter = new ProfilesArrayAdapter(this, R.layout.profile_list_row, val);

	    lv = (ListView)findViewById(android.R.id.list);
	    lv.setAdapter(profilesArrayAdapter);
	    /*lv.setOnItemClickListener(new OnItemClickListener()
	    {
			@Override
			public void onItemClick(AdapterView<?> a, View view, int position, long id) {
				DoctorProfile doctorProfile = (DoctorProfile) a.getAdapter().getItem(position);
				
				Intent mainprofile = new Intent(ProfileListActivity.this, BlueOrGreenEditFormActivity.class);
				mainprofile.putExtra("autorisationData", autorisationData);
				mainprofile.putExtra("profileNumber", doctorProfile.Id);
				mainprofile.putExtra("screenIdx", Constants.SCREEN_ONE);
				mainprofile.putExtra("form", form);

				startActivity(mainprofile);
			}
	    });*/

	    if(form == Constants.FORM_BLUE){
	    	applicationType = Constants.SIMPLE_PROFILE;
	    }else{
	    	applicationType = Constants.COMMUNITY_PROFILE;
	    }
		new GetAllProfilesTask(this, autorisationData, applicationType).execute();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.popup_menu:
			Intent popUpMenuActivity = new Intent(this, PopUpMenuActivity.class);
			popUpMenuActivity.putExtra("autorisationData", autorisationData);
			startActivityForResult(popUpMenuActivity, Constants.REQUEST_ACTIVITY_CLOSE_STATE);
		break;
		case R.id.wrapper_header_backbutton_lbl:
			finish();
			break;
		}
		
	}

    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
	    	case (Constants.REQUEST_ACTIVITY_CLOSE_STATE) : {	// Also can be Constants.EXIT_TO_MAIN_MENU
	    		if(resultCode != Constants.NORMAL_CLOSE){
					Intent resultIntent = new Intent();
					setResult(resultCode, resultIntent);	// Close current form. Send Form to parent
					finish();
	    		}
	    		break;
	    		}
	    	}
	  }

    @Override
    public void onBackPressed() {
		Intent resultIntent = new Intent();
		setResult(Constants.NORMAL_CLOSE, resultIntent);
		finish();
    }

	@Override
	public void onProfilesLoad(ArrayList<DoctorProfile> doctorProfiles) {
		profilesArrayAdapter.clear();
		for(DoctorProfile doctorProfile : doctorProfiles){
			profilesArrayAdapter.add(doctorProfile);
		}
		profilesArrayAdapter.notifyDataSetChanged();
	}

	@Override
	public void onProfilesEdit(final DoctorProfile doctorProfile) {
		Intent mainprofile = new Intent(ProfileListActivity.this, BlueOrGreenEditFormActivity.class);
		mainprofile.putExtra("autorisationData", autorisationData);
		mainprofile.putExtra("profileNumber", doctorProfile.Id);
		mainprofile.putExtra("profileName", doctorProfile.Name);
		mainprofile.putExtra("screenIdx", Constants.SCREEN_ONE);
		mainprofile.putExtra("form", form);
		mainprofile.putExtra("isMain", false);
		startActivity(mainprofile);
	}

	@Override
	public void onProfilesDelete(final DoctorProfile doctorProfile) {
		new AlertDialog.Builder(this)
		.setTitle("Profile (Stub)")
		.setMessage("Delete profile? (Stub)")
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) { 
		        finish();
		    }
		 })
		.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) { 
		        //finish();
		    }
		 })
		.setIcon(android.R.drawable.ic_dialog_alert)
		.show();
	}

}

