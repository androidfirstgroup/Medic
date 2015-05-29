package com.medicine.ima;

// <copyright file="ClinicActivity.java" company="private">
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
// <date>2014-10-20</date>
// <summary>Medic organization profile</summary>

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ClinicActivity extends Activity implements OnClickListener{
	ImageView popup_menu;
	Button mainprofile, secondProfile, btDocument1, btDocument2;
	private String autorisationData;
	private TextView btnBack;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cliniclayout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Bundle bundle = getIntent().getExtras();
		autorisationData = bundle.getString("autorisationData");
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		popup_menu = (ImageView) findViewById(R.id.popup_menu);
        mainprofile = (Button) findViewById(R.id.btMainProfile);
        secondProfile = (Button) findViewById(R.id.btSecondProfile);
        btDocument1 = (Button) findViewById(R.id.btDocument1);
        btDocument2 = (Button) findViewById(R.id.btDocument2);
		popup_menu.setOnClickListener(this);
		mainprofile.setOnClickListener(this);
		secondProfile.setOnClickListener(this);
		btDocument1.setOnClickListener(this);
		btDocument2.setOnClickListener(this);
		btnBack = (TextView)findViewById(R.id.wrapper_header_backbutton_lbl);
		btnBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.popup_menu:
			Intent popUpMenuActivity = new Intent(this, PopUpMenuActivity.class);
			popUpMenuActivity.putExtra("autorisationData", autorisationData);
			startActivityForResult(popUpMenuActivity, Constants.REQUEST_ACTIVITY_CLOSE_STATE);
		break;
		case R.id.btMainProfile:
			Intent mainprofile = new Intent(this, BlueOrGreenEditFormActivity.class);
			mainprofile.putExtra("autorisationData", autorisationData);
			mainprofile.putExtra("profileNumber", -1);
			mainprofile.putExtra("profileName", "");
			mainprofile.putExtra("screenIdx", Constants.SCREEN_ONE);
			mainprofile.putExtra("form", Constants.FORM_GREEN);
			startActivityForResult(mainprofile, Constants.REQUEST_ACTIVITY_CLOSE_STATE);
		break;

		case R.id.btSecondProfile:
			Intent profileListActivity = new Intent(this, ProfileListActivity.class);
			profileListActivity.putExtra("autorisationData", autorisationData);
			profileListActivity.putExtra("form", Constants.FORM_GREEN);
			startActivityForResult(profileListActivity, Constants.REQUEST_ACTIVITY_CLOSE_STATE);
			break;
		
		case R.id.btDocument1:
			Intent document1 = new Intent(this, Document1Activity.class);
			startActivity(document1);
		break;
		case R.id.btDocument2:
			Intent document2 = new Intent(this, Document2Activity.class);
			startActivity(document2);
			break;
		case R.id.wrapper_header_backbutton_lbl:
			finish();
			break;
		}
	}
	
    @Override
    public void onBackPressed() {
		Intent resultIntent = new Intent();
		setResult(Constants.NORMAL_CLOSE, resultIntent);
		finish();
    }

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
	    	case (Constants.REQUEST_ACTIVITY_CLOSE_STATE) : {
	    		if ( resultCode != Constants.GO_TO_FORM_GREEN && resultCode != Constants.NORMAL_CLOSE) {
					Intent resultIntent = new Intent();
					setResult(resultCode, resultIntent);	// Close current form. Send Form to parent
					finish();
	    		}
	    		break;
	    	}
		}
	}
}
