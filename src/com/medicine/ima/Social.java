package com.medicine.ima;

import java.util.ArrayList;

import com.medicine.ima.ProfileMap.ProfileParam;

public class Social implements Comparable<Social>{
    private String lblText = null;
    private String valText = null;
	private String key;
	private int sectionIdx;
	private boolean clickable;
	private int screenIdx;
	private int typeValue;
	private ArrayList<String> unixTimeList;
	private int idx;


	public Social(String lblTaxt, String valText, String key, int sectionIdx, int screenIdx, boolean clickable) {
    	this.lblText = lblTaxt;
        this.valText = valText;
        this.key = key;
        this.sectionIdx = sectionIdx;
        this.screenIdx = screenIdx;
		this.clickable = clickable;
    }

    public Social(ProfileParam value, boolean clickable) {
    	this.lblText = value.label;
        this.valText = value.value;
        this.key = value.key;
        this.sectionIdx = value.sectionIdx;
        this.screenIdx = value.screenIdx;
		this.clickable = clickable;
		this.typeValue = value.typeValue;
		if(value.unixTimeList !=null){
			this.unixTimeList = new ArrayList<String>();
			this.unixTimeList.addAll(value.unixTimeList);
		}
		this.idx = value.idx;
	}

    void clearUnixTime(){
    	if(this.unixTimeList != null){
    		this.unixTimeList.clear();
    	}
    }
    
	ArrayList<String> getUnixTimeList(){
		return unixTimeList;
	}

    
    int getTypeValue(){
    	return typeValue;
    }
    
	int getScreenIdx(){
    	return screenIdx;
    }
    
	int getSectionIdx(){
    	return sectionIdx;
    }
    
    String getKey(){
    	return key;
    }
    
    String getLabel(){
    	return lblText;
    }

    void setValue(String valText){
    	this.valText = valText;
    }

    String getValue(){
    	return valText;
    }

	@Override
	public int compareTo(Social social) {
		return this.idx - social.idx;	
		}

	public boolean isClickable() {
		return clickable;
	}

	public void setUnixTimeList(ArrayList<String> strDatesArray) {
		if(strDatesArray != null && strDatesArray.size() > 0){
			valText = String.valueOf(strDatesArray.size());
			if(unixTimeList == null){
				unixTimeList = new ArrayList<String>();
			}else{
				unixTimeList.clear();
			}
			unixTimeList.addAll(strDatesArray);
		}else{
			valText = "0";
		}
	}

}
