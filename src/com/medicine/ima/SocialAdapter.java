package com.medicine.ima;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class SocialAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> groups;
    private ArrayList<ArrayList<Social>> socials;
    private LayoutInflater inflater;
    private ArrayList<ImageView> iconImageList = new ArrayList<ImageView>();

    public SocialAdapter(Context context, 
                        ArrayList<String> groups,
						ArrayList<ArrayList<Social>> socials ) { 
        this.context = context;
		this.groups = groups;
        this.socials = socials;
        inflater = LayoutInflater.from( context );
    }

    public Object getChild(int groupPosition, int childPosition) {
        return socials.get( groupPosition ).get( childPosition );
    }

    public long getChildId(int groupPosition, int childPosition) {
        return (long)( groupPosition*1024+childPosition );  // Max 1024 children per group
    }

    Social social;
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = null;
        ImageView iconDate = null;
        social = (Social)getChild( groupPosition, childPosition );
        
        if( convertView != null )
            v = convertView;
        else
    		v = inflater.inflate(R.layout.child_row, parent, false);
        	iconDate = (ImageView)v.findViewById(R.id.icon_date);
        	
        	  // set the ClickListener to handle the click event on child item
        	iconDate.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                	social = (Social)getChild( groupPosition, childPosition );
                    ((BlueOrGreenEditFormActivity)(SocialAdapter.this.context)).onCalendarShow(social, groupPosition, childPosition);
                    //Toast.makeText(context, social.getLabel(),
                     //       Toast.LENGTH_SHORT).show();
                }
            });

            if(convertView == null){
                TextView value = (TextView) v.findViewById(R.id.number);

                switch(getChildType(groupPosition, childPosition)){
                 case LAYOUT_TYPE_1: 
                     //value.setVisibility(View.VISIBLE);
                     break;

                 case LAYOUT_TYPE_2:
                     value.setVisibility(View.GONE);
                     break;
                 
                 case LAYOUT_TYPE_3:
             		iconDate.setVisibility(View.VISIBLE);
                    break;
                }
            }
        	
		TextView label = (TextView)v.findViewById( R.id.childname );
		if( label != null )
			label.setText( social.getLabel() );
        TextView value = (TextView) v.findViewById(R.id.number);
		if( value != null )
			value.setText( social.getValue() );
        return v;
    }

    public int getChildrenCount(int groupPosition) {
        return socials.get( groupPosition ).size();
    }

    public Object getGroup(int groupPosition) {
        return groups.get( groupPosition );        
    }

    public int getGroupCount() {
        return groups.size();
    }

    public long getGroupId(int groupPosition) {
        return (long)( groupPosition*1024 );  // To be consistent with getChildId
    } 

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = null;
        if( convertView != null )
            v = convertView;
        else
            v = inflater.inflate(R.layout.group_row, parent, false); 
        String gt = (String)getGroup( groupPosition );
		TextView colorGroup = (TextView)v.findViewById( R.id.childname );
		if( gt != null )
			colorGroup.setText( gt );
        return v;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    } 

    public void onGroupCollapsed (int groupPosition) {} 
    public void onGroupExpanded(int groupPosition) {}

    private static final int LAYOUT_TYPE_1 = 0;
    private static final int LAYOUT_TYPE_2 = 1;
    private static final int LAYOUT_TYPE_3 = 2;

    private static final int LAYOUT_TYPE_COUNT = 3;
    

    @Override
    public int getChildType(int groupPosition, int childPosition) {

                // Return a number here, 1 to whatever you return in getChildTypeCount.
                // Each number should correspond to a particular layout, using group
                // and child position to determine which layout to produce. 

        Social c = (Social)getChild( groupPosition, childPosition );
    	if(c != null && c.isClickable()){
    		if(c.getTypeValue() == ProfileMap.TYPE_VALUE_UNIXTYPE)
                return LAYOUT_TYPE_3;
    		else
    			return LAYOUT_TYPE_1;
    	}else{
            return LAYOUT_TYPE_2;
    	}

        
        //return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public int getChildTypeCount() {

                // Return the number of distinct layouts you expect to create
    	return LAYOUT_TYPE_COUNT;
        //return super.getChildTypeCount();
    }

}
