package com.medicine.ima;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SendPassword extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	String person_email, cell_phone, head, message, pass;
	Button cancel, ok;
	RadioGroup smsEmail;
	RadioButton sms, email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendlayout);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		cancel = (Button) findViewById(R.id.btCancel);
		ok = (Button) findViewById(R.id.btOk);
		smsEmail = (RadioGroup) findViewById(R.id.rgSmsEmail);
		sms = (RadioButton) findViewById(R.id.rbSms);
		email = (RadioButton) findViewById(R.id.rbEmail);
		cancel.setOnClickListener(this);
		ok.setOnClickListener(this);
		smsEmail.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btOk:
			if(sms.isChecked()) {
				//sendSms(cell_phone,"luck");
			} else if(email.isChecked()) {
				sendEmail(person_email,"pass");
			}
			break;
		case R.id.btCancel:
			finish();
			break;
		}
	}

	

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId) {
			case R.id.rbSms:
				Log.i("SMS", "send");
			break;
			case R.id.rbEmail:
				Log.i("EMAIL", "send");
			break;
		}
	}
	
	private void sendSms(String cell, String pass) {
		cell_phone = cell;
		String message = "Your password is" + pass;
		try {
			SmsManager sm =  SmsManager.getDefault();
			sm.sendTextMessage(cell_phone, null, message, null, null);
			Toast.makeText(getBaseContext(), "Password sent by SMS", Toast.LENGTH_SHORT).show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendEmail(String email, String pass) {
		// TODO Auto-generated method stub
		person_email = email;
		String message = "Hello, your pass is" + pass;
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, person_email);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Your email");
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
	}

}
