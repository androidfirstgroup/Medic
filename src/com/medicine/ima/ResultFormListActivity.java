package com.medicine.ima;

import java.util.ArrayList;

import com.medicine.ima.ProfileMap.ResultForm;
import com.medicine.ima.jsontasks.GetResultFormTask;
import com.medicine.ima.jsontasks.IProfileResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class ResultFormListActivity extends Activity implements OnClickListener, IProfileResult{
	private Context context;
	private ResultFormArrayAdapter resultFormArrayAdapter;
	private String autorisationData;

//	int SIMPLE_PROFILE = 1;
//	int COMMUNITY_PROFILE = 2;

	ListView lv;
	private int form;
	private int profileNumber;
	private int screenIdx;
	private boolean isMain;

	ArrayList<ResultProfile> resultFormRows = new ArrayList<ResultProfile>();
	private ImageView popup_menu;
	private TextView btnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_list_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		context = this;
		Bundle bundle = getIntent().getExtras();
		autorisationData = bundle.getString("autorisationData");
		form = bundle.getInt("form");
		profileNumber = bundle.getInt("profileNumber");
		screenIdx = bundle.getInt("screenIdx");
		form = bundle.getInt("form");
		isMain = bundle.getBoolean("isMain", true);

		// Heder menu items
		popup_menu = (ImageView) findViewById(R.id.popup_menu);
 		popup_menu.setOnClickListener(this);
		btnBack = (TextView)findViewById(R.id.wrapper_header_backbutton_lbl);
		btnBack.setOnClickListener(this);


		resultFormArrayAdapter = new ResultFormArrayAdapter(this, R.layout.result_list_row, resultFormRows, form);

	    lv = (ListView)findViewById(android.R.id.list);
	    lv.setAdapter(resultFormArrayAdapter);

		new GetResultFormTask(this, autorisationData, profileNumber, form, isMain).execute();
		
		
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
    public void onBackPressed() {
		Intent resultIntent = new Intent();
		setResult(Constants.NORMAL_CLOSE, resultIntent);
		finish();
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
	public void onProfileResultLoad(ArrayList<ResultForm> resultOneForm) {
		int i=0, j=0;
		for(ResultForm resultOneFormGroup : resultOneForm ){
			int groupColor = getGroupColor(i, j);
			resultFormRows.add(new ResultProfile(ResultForm.TYPE_GROUP, resultOneFormGroup.title, resultOneFormGroup.value, 
					groupColor));
			j=0;
			//System.out.println("OneForm group:" + resultOneFormGroup.title );
			for(ResultForm resultOneFormChild : resultOneFormGroup.sections ){
				int colorItem =  getcolorItem(i,j);
				resultFormRows.add(new ResultProfile(ResultForm.TYPE_CHILD, resultOneFormChild.title, resultOneFormChild.value,
						colorItem));
				ResultProfile resultForm = createSubHeader(i,j);
				if(resultForm != null){
					resultFormRows.add(resultForm);
				}
				j++;
				//System.out.println("    OneForm child:" + resultOneFormChild.title + " : value=" + resultOneFormChild.value );
			}
			i++;
		}
		resultFormArrayAdapter.notifyDataSetChanged();		
	}


	private int getcolorItem(int i, int j) {
		int colorTotaltem = R.color.white;
		/*switch (form) {
		case Constants.FORM_GREEN:
			if(i==2 && j==4)
				colorTotaltem = R.color.light_blue_2;
			break;
		default:
			colorTotaltem = R.color.white;
			break;
		}*/
		return colorTotaltem;
	}


	public int getGroupColor(int i, int j){
		int group_color = 0;
		switch (form) {
		case Constants.FORM_GREEN:
			if( i == 0 && j == 0){
				group_color = R.color.light_green;
			}else{
				if( i==4 && j==4 ){
					group_color = R.color.light_green;
				}else{
					group_color = R.color.light_green_2;
				}
			}
			break;
		case Constants.FORM_BLUE:
			System.out.println("i=" + i + ": j=" + j);
			if( j == 0 && j == 0 ){
				group_color = R.color.light_blue;
			}else if( i==5 && j==5 ){
				group_color = R.color.light_blue;
			}else if( i==6 && j==2 ){
				group_color = R.color.light_blue;
			}else{
				group_color = R.color.light_blue_2;
			}
			
			break;

		default:
			break;
		}
		return group_color;
	}

	
	private ResultProfile createSubHeader(int i, int j) {
		ResultProfile resultProfile = null;
		
		switch (form) {
		case Constants.FORM_GREEN:
			if(i==0 && j==0)
				resultProfile = new ResultProfile(ResultForm.TYPE_GROUP, "תוספות קבועות לערך יום", "", R.color.light_green);
			else
			if(i==2 && j==4)
				resultProfile = new ResultProfile(ResultForm.TYPE_GROUP, "תוספות קבועות לא לערך יום", "", R.color.light_green);
			else
			//if(i==4 && j==4)
			//	resultProfile = new ResultProfile(ResultForm.TYPE_GROUP, "ערך יום - לצורך חישוב עבודה נוספת", "", R.color.light_green);
			//else
			if(i==5 && j==2)
				resultProfile = new ResultProfile(ResultForm.TYPE_GROUP, "עבודה נוספת", "", R.color.light_green);
			else
				if(i==10 && j==2)
					resultProfile = new ResultProfile(ResultForm.TYPE_GROUP, "תוצאות", "", R.color.light_green);
			break;
		case Constants.FORM_BLUE:
			if(i==0 && j==0)
				resultProfile = new ResultProfile(ResultForm.TYPE_GROUP, "תוספות קבועות לערך יום", "", R.color.light_blue);
			else
				if(i==2 && j==4)
					resultProfile = new ResultProfile(ResultForm.TYPE_GROUP, "תוספות קבועות לא לערך יום", "", R.color.light_blue);
				else
					if(i==13 && j==2)
						resultProfile = new ResultProfile(ResultForm.TYPE_GROUP, "תוצאות", "", R.color.light_blue);
			break;
		default:
			break;
		}
		return resultProfile;
	}

}
