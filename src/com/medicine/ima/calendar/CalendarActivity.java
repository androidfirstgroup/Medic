/*
 * Copyright (C) 2011 Chris Gao <chris@exina.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.medicine.ima.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.medicine.ima.BlueOrGreenEditFormActivity;
import com.medicine.ima.PopUpMenuActivity;
import com.medicine.ima.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarActivity extends Activity  implements CalendarView.OnCellTouchListener, OnClickListener{
	public static final String MIME_TYPE = "vnd.android.cursor.dir/vnd.exina.android.calendar.date";
	CalendarView mView = null;
	TextView mHit;

	TextView backButton, saveButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        
        
        backButton = (TextView) findViewById(R.id.wrapper_header_backbutton_lbl);
        backButton.setOnClickListener(this);
        saveButton = (TextView) findViewById(R.id.wrapper_header_savebtn_lbl);
        saveButton.setOnClickListener(this);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		

        mView = (CalendarView)findViewById(R.id.calendar);
        mView.setOnCellTouchListener(this);
        
  
        Bundle bundle = getIntent().getExtras();             
        ArrayList<String> strDatesArray = bundle.getStringArrayList("Dates");
    	Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        if(strDatesArray != null){
	        for(String strDate : strDatesArray){
	            try {
	            	calendar.setTime(sdf.parse(strDate));
	            	calendar.get(Calendar.DATE);
	            	calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	            } catch (ParseException e) {
					e.printStackTrace();
				}
	         }
        }

        mView.setDayList(strDatesArray);
    }

    //
    //
    //
	public void onTouch(Cell cell) {
	
    	mView.switchSelectedDay(cell);
		mView.invalidate();

	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
		case R.id.wrapper_header_backbutton_lbl:
			setResult(Activity.RESULT_CANCELED);
			finish();
			/*
	    	new AlertDialog.Builder(this)
	        .setTitle(R.string.save_calendar_comfirmation_header_text)
	        .setMessage(R.string.save_calendar_comfirmation_text)
	        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) { 
	    			Intent resultIntentSave = new Intent();
	    			setResult(Activity.RESULT_OK, resultIntentSave);
	    			finish();
	            }
	         })
	        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) { 
	    			setResult(Activity.RESULT_CANCELED);
	    			finish();
	            }
	         })
	        .setIcon(android.R.drawable.ic_dialog_alert)
	         .show();
	    	*/
			break;
		case R.id.wrapper_header_savebtn_lbl:
			Intent resultIntent = new Intent();
			setResult(Activity.RESULT_OK, resultIntent);
			resultIntent.putExtra("Dates", mView.getSelectedDatesList());
			finish();
			break;
		}
	}
}
