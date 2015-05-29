package com.medicine.ima;

// <copyright file="PopUpMenuActivity.java" company="private">
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


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class PopUpMenuActivity extends Activity {

    private String autorisationData;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_menu_layout);

		Bundle bundle = getIntent().getExtras();
		autorisationData = bundle.getString("autorisationData");
    }

    public void form_green(View view){
		Intent resultIntent = new Intent();
		setResult(Constants.GO_TO_FORM_GREEN, resultIntent);
		finish();
    }

    public void form_blue(View view){
		Intent resultIntent = new Intent();
		setResult(Constants.GO_TO_FORM_BLUE, resultIntent);
		finish();
    }
    
    public void app_for_congress_dotctors(View view){
/*    	new AlertDialog.Builder(this)
        .setTitle("App")
        .setMessage("Congress app")
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                finish();
            }
         })
        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                finish();
            }
         })
        .setIcon(android.R.drawable.ic_dialog_alert)
         .show();
         */
    }


    public void item_popup_4(View view){
        //finish();
    	
    }
    public void item_popup_5(View view){
		Intent resultIntent = new Intent();
		setResult(Constants.EXIT_TO_MAIN_MENU, resultIntent);
		finish();    	
    }
    
    @Override
    public void onBackPressed() {
		Intent resultIntent = new Intent();
		setResult(Constants.NORMAL_CLOSE, resultIntent);
		finish();
    }

}


