package com.medicine.ima;

import java.util.List;

import com.medicine.ima.ProfileMap.ResultForm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultFormArrayAdapter extends ArrayAdapter<ResultProfile>{

	  private static final int COUNT_TYPE_ROWS = 2;
	  private final Context context;
	  private final List<ResultProfile> resultProfileList;
	  private int layoutResourceId;
	  private int form;

	  public ResultFormArrayAdapter(Context context, int layoutResourceId, List<ResultProfile> resultProfileList, int form) {
	    super(context, layoutResourceId, resultProfileList);
	    this.layoutResourceId = layoutResourceId;
	    this.context = context;
	    this.resultProfileList = resultProfileList;
	    this.form = form;
	  }

	  @Override
	  public int getItemViewType(int position) {
	       int type = resultProfileList.get(position).type_row;
	       if (type == ResultForm.TYPE_GROUP) {
	            return ResultForm.TYPE_GROUP;
	       } else {
	            return ResultForm.TYPE_CHILD;
	       }
	  }

	  @Override
	  public int getViewTypeCount() {
		return COUNT_TYPE_ROWS;
	  }
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  View row = convertView;
		  ResultProfilesHolder holder = null;

      	
	        if(row == null)
	        {	        	
			    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			    
			    row = inflater.inflate(layoutResourceId, parent, false);
			    holder = new ResultProfilesHolder();

			    holder.textLbl = (TextView)row.findViewById(R.id.textLbl);
			    holder.textVal = (TextView)row.findViewById(R.id.textVal);
	            row.setTag(holder);

	        }

	        holder = (ResultProfilesHolder)row.getTag();
            holder.textLbl.setText(resultProfileList.get(position).title);
            holder.textVal.setText(resultProfileList.get(position).value);

            int type = getItemViewType(position);
  		  	if(type == ResultForm.TYPE_GROUP){
  		  		holder.textVal.setVisibility(View.GONE);
  		  		//if(form == Constants.FORM_GREEN){
  		  		//	holder.textLbl.setBackgroundResource(R.color.light_green_2);
  		  		//}else{
  		  		//	holder.textLbl.setBackgroundResource(R.color.light_blue_2);
  		  		//}
  		  		holder.textLbl.setBackgroundResource(resultProfileList.get(position).color);
  		  		holder.textVal.setTypeface(null, Typeface.BOLD);
  		  	}

	    return row;
	  }

	  static class ResultProfilesHolder
	    {
	        //int type;
		  	TextView textVal;
	        TextView textLbl;
	    }
}

