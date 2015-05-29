package com.medicine.ima;

// <copyright file="MainActivity.java" company="private">
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
// <date>2014-10-18</date>
// <summary>Medic organization profile</summary>

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.medicine.ima.jsontasks.GetAutorizationTask;
import com.medicine.ima.wheel.DatePickerDailog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener, IAutorization {

//	Timestamp timeStampBirth;
	private String TAG = ">>>MainActivity";
	
	ImageButton cancel, ok; 
	Button recover;

	Calendar dateandtime;
	EditText edBirth;

	private EditText edPass;
	private EditText edLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(!AppSettings.isNetworkAvailable(this)){
			AppSettings.serviceErrorMessage(this, true);
		}
		dateandtime = Calendar.getInstance(Locale.US);

		initialize();
	}

	private void initialize() {
		cancel = (ImageButton) findViewById(R.id.btCancel);
		ok = (ImageButton) findViewById(R.id.btOk);
		recover = (Button) findViewById(R.id.btRecover);
		cancel.setOnClickListener(this);
		ok.setOnClickListener(this);
		recover.setOnClickListener(this);
		edLogin = (EditText)findViewById(R.id.edLogin);
		edPass = (EditText)findViewById(R.id.edPass);
		
		edBirth = (EditText) findViewById(R.id.edBirth);
		edBirth.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        if (event.getAction() == MotionEvent.ACTION_UP) {
		            //showDialog(DIALOG_DATE_PICKER);
		        	
					DatePickerDailog dp = new DatePickerDailog(MainActivity.this,
							dateandtime, new DatePickerDailog.DatePickerListner() {

								@Override
								public void OnDoneButton(Dialog datedialog, Calendar c) {
									datedialog.dismiss();
									//dateandtime.set(Calendar.YEAR, c.get(Calendar.YEAR));
									//dateandtime.set(Calendar.MONTH,
									//		c.get(Calendar.MONTH));
									//dateandtime.set(Calendar.DAY_OF_MONTH,
									//		c.get(Calendar.DAY_OF_MONTH));
									dateandtime.setTimeInMillis(System.currentTimeMillis());
									dateandtime.clear();
									dateandtime.set(Calendar.YEAR, 1901);
									dateandtime.set(Calendar.MONTH,	Calendar.JANUARY);
									dateandtime.set(Calendar.DAY_OF_MONTH, 1);
									edBirth.setText(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(c.getTime()));
								}

								@Override
								public void OnCancelButton(Dialog datedialog) {
									// TODO Auto-generated method stub
									datedialog.dismiss();
								}
							});
					dp.show();

		        	
		        }
		        return false;
		    }
		});		
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btCancel:
			finish();
			break;
		case R.id.btOk:
			Date date;
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
				date = (Date)formatter.parse(edBirth.getText().toString());
				long timeStampLong = date.getTime();
				new GetAutorizationTask(this, edLogin.getText().toString(), edPass.getText().toString(), timeStampLong/1000).execute();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case R.id.btRecover:
			Intent recover = new Intent(this, RecoveryLoginActivity.class);
			startActivity(recover);
			break;
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		//finish();
	}

	@Override
	public void onAutorization(Object obj) {
		if(obj!=null){
			String autorisationData = (String)obj;
			Log.d(TAG , "autorisationData = " + autorisationData);
			Intent programs = new Intent(this, ProgramList.class);
			programs.putExtra("autorisationData", autorisationData);
			startActivity(programs);
		}
		
	}
	
}
