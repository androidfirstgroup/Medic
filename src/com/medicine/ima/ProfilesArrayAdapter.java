package com.medicine.ima;

// <copyright file="ProfileArrayAdapter.java" company="private">
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

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilesArrayAdapter extends ArrayAdapter<DoctorProfile>{


	  private final Context context;
	  private final List<DoctorProfile> values;
	  private int layoutResourceId;
	  IDoctorProfiles profileInterface;

	  public ProfilesArrayAdapter(Context context, int layoutResourceId, List<DoctorProfile> values) {
	    super(context, layoutResourceId, values);
	    profileInterface = (IDoctorProfiles)context;
	    this.layoutResourceId = layoutResourceId;
	    this.context = context;
	    this.values = values;
	  }

	  @Override
	  public View getView(final int position, View convertView, ViewGroup parent) {
		  View row = convertView;
		  DoctorProfilesHolder holder = null;

	        if(row == null)
	        {
			    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			    
			    row = inflater.inflate(layoutResourceId, parent, false);
			    holder = new DoctorProfilesHolder();
	            holder.editIcon = (ImageView)row.findViewById(R.id.imageDel);
	            holder.delIcon = (ImageView)row.findViewById(R.id.imageEdit);
	            holder.textRow = (TextView)row.findViewById(R.id.textRow);

	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (DoctorProfilesHolder)row.getTag();
	        }

	        holder.textRow.setText(values.get(position).Name);
            holder.editIcon.setImageResource(R.drawable.edit_icon);
            holder.delIcon.setImageResource(R.drawable.del_icon);
            
      	  // set the ClickListener to handle the click event on child item
            holder.editIcon.setOnClickListener(new OnClickListener() {
            	@Override
            	public void onClick(View view) {
            		profileInterface.onProfilesEdit(values.get(position));
            	}
            });
            holder.delIcon.setOnClickListener(new OnClickListener() {
            	@Override
            	public void onClick(View view) {
            		profileInterface.onProfilesDelete(values.get(position));
            	}
            });

            return row;
	  }

	  static class DoctorProfilesHolder
	    {
	        ImageView editIcon;
	        ImageView delIcon;
	        TextView textRow;
	    }


}
