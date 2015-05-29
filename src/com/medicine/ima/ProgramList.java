package com.medicine.ima;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ProgramList extends Activity implements OnClickListener{

	Button logout;
	ImageButton salary1, salary2, congress;
	private String autorisationData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programlistlayout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Bundle bundle = getIntent().getExtras();
		autorisationData = bundle.getString("autorisationData");

		initialize();
	}
	
	private void initialize() {
		logout = (Button) findViewById(R.id.bt_out);
		salary1 = (ImageButton) findViewById(R.id.btnClinic);
		salary2 = (ImageButton) findViewById(R.id.btnHospital);
		congress = (ImageButton) findViewById(R.id.btCongress);
		logout.setOnClickListener(this);
		salary1.setOnClickListener(this);
		salary2.setOnClickListener(this);
		congress.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.bt_out:
			finish();
		break;
		case R.id.btnClinic:
			Intent clinic = new Intent(this, ClinicActivity.class);
			clinic.putExtra("autorisationData", autorisationData);
			startActivityForResult(clinic, Constants.REQUEST_ACTIVITY_CLOSE_STATE);
			break;
		case R.id.btnHospital:
			Intent hospital = new Intent(this, HospitalActivity.class);
			hospital.putExtra("autorisationData", autorisationData);
			startActivityForResult(hospital, Constants.REQUEST_ACTIVITY_CLOSE_STATE);
			break;
		case R.id.btCongress:
			
			break;
	
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	    	if (requestCode == Constants.REQUEST_ACTIVITY_CLOSE_STATE){
	    		if ( resultCode == Constants.GO_TO_FORM_GREEN ) {
	    			Intent clinic = new Intent(this, ClinicActivity.class);
	    			clinic.putExtra("autorisationData", autorisationData);
	    			startActivityForResult(clinic, Constants.REQUEST_ACTIVITY_CLOSE_STATE);
	    		}else{
	    			if(resultCode == Constants.GO_TO_FORM_BLUE){
		    			Intent hospital = new Intent(this, HospitalActivity.class);
		    			hospital.putExtra("autorisationData", autorisationData);
		    			startActivityForResult(hospital, Constants.REQUEST_ACTIVITY_CLOSE_STATE);
	    			}
	    		}
	    	}
	}
}


