package com.medicine.ima;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RecoveryLoginActivity extends Activity implements OnClickListener {

	Button cancel, ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reclayout);
		initialize();
	}

	private void initialize() {
		cancel = (Button) findViewById(R.id.btCancel);
		ok = (Button) findViewById(R.id.btOk);
		cancel.setOnClickListener(this);
		ok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btCancel:
			finish();
			break;
		case R.id.btOk:
			Intent SendPass = new Intent(this, SendPassword.class);
			startActivity(SendPass);
			break;

		}
	}

}
