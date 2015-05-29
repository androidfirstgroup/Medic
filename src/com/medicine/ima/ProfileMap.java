package com.medicine.ima;

// <copyright file="ProfileMap.java" company="private">
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



import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.medicine.ima.ProfileMap.ProfileParam;

import android.content.Context;
import android.text.format.DateFormat;

public class ProfileMap {

	class ProfileParam{
		int sectionIdx;
		String key;
		String label;
		String value;
		String serverValue;
		ArrayList<String> labelList;
		ArrayList<String> valueList;
		ArrayList<String> unixTimeList;
		int typeValue;
		int screenIdx;
		int idx;

		public ProfileParam(int sectionIdx, int screenIdx, String key, String label, String value, 
				ArrayList<String> labelList, ArrayList<String> valueList, int typeValue) {
			this.sectionIdx = sectionIdx;
			this.screenIdx = screenIdx;
			this.key = key;
			this.label = label;
			this.value = value;
			this.labelList = labelList;
			this.valueList = valueList;
			this.typeValue = typeValue;
		}
		void clearUnixTimeList(){
			if(unixTimeList!=null){
				unixTimeList.clear();
			}
		}
	}


	private static final int SECTION_1 = 0;
	private static final int SECTION_2 = 1;
	private static final int SECTION_3 = 2;
	private static final int SECTION_4 = 3;
	private static final int SECTION_5 = 4;
	private static final int SECTION_6 = 5;
	private static final int SECTION_7 = 6;
	private static final int SECTION_8 = 7;
	private static final int SECTION_9 = 7;
	
	public static final int TYPE_VALUE_STRING = 1;
	public static final int TYPE_VALUE_STRING_LIST = 2;
	public static final int TYPE_VALUE_INT = 3;
	public static final int TYPE_VALUE_INT_LIST = 4;
	public static final int TYPE_VALUE_FLOAT = 5;
	public static final int TYPE_VALUE_FLOAT_LIST = 6;
	public static final int TYPE_VALUE_BOOLEAN = 7;
	public static final int TYPE_VALUE_UNIXTYPE = 8;


	/*
	 * 
	 * 
	 * 	Constructor Init some data structures
	 * 
	 * 
	 */
	public ProfileMap(){

		initResultOneForm();

		initResultTwoForm();
		
		
		/*
		 * 
		 * 	Blue Form
		 * 
		 */
		//------------------- Section 1------------------------------------
		
		//-------------------- ddlMaamad ------------------------------------
		profileParamBlueForm.put("ddlMaamad", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlMaamad", "מעמד", "Text", new ArrayList<String>() {{
		    add("מתמחה");
		    add("רופא תחומי");
		    add("מומחה");
		    add("מנהל שירות");
		    add("מנהל");
		}},
		new ArrayList<String>() {{
			add("1");
			add("2");
			add("3");
			add("4");
			add("5");
		}},
		TYPE_VALUE_STRING_LIST));

		//--------------------- ddlDarga ----------------------------------
		profileParamBlueForm.put("ddlDarga", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlDarga", "דרגה", "Text", new ArrayList<String>() {{
			add("2");
			add("3");
			add("3.5");
			add("4");
			add("5");
			add("6");
			add("6.5");
			add("7");
			add("7.5");
			add("8");
			add("8.5");
			add("9");
			add("9.5");
			add("10");
			add("10.5");
			add("11");
			add("11.5");
			add("12");
			add("12.5");
		}},
		
		new ArrayList<String>() {{
			add("2");
			add("3");
			add("3.5");
			add("4");
			add("5");
			add("6");
			add("6.5");
			add("7");
			add("7.5");
			add("8");
			add("8.5");
			add("9");
			add("9.5");
			add("10");
			add("10.5");
			add("11");
			add("11.5");
			add("12");
			add("12.5");
		}},
		TYPE_VALUE_FLOAT_LIST));
	
		//------------------ ddlDargaDocThumi --------------------------------------
		profileParamBlueForm.put("ddlDargaDocThumi", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlDargaDocThumi", "דרגה לרופא תחומי", "Text", 
				null, null, TYPE_VALUE_INT));

		//----------------- txtVetek -----------------------------------------------
		profileParamBlueForm.put("txtVetek", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "txtVetek", "ותק לשכר (ציין/י את הוותק במועד הנבחר)", "Text", 
				null, null, TYPE_VALUE_INT));
		
		//----------------- ddlNotMenahel ------------------------------------------
		profileParamBlueForm.put("ddlNotMenahel", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlNotMenahel", "סוג מנהל", "Text", new ArrayList<String>() {{
			add("לא רלוונטי");
		}},
		new ArrayList<String>() {{
			add("0");
		}},	TYPE_VALUE_BOOLEAN));

		//----------------- ddlMumxeHitmahutAl ------------------------------------
		profileParamBlueForm.put("ddlMumxeHitmahutAl", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlMumxeHitmahutAl", "במהלך התמחות על", "Text", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));
		
		//----------------- ddlMaasik --------------------------------------------		
		profileParamBlueForm.put("ddlMaasik", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlMaasik", "מדינה", "Text", new ArrayList<String>() {{
			add("מדינה");
			add("כללית");
			add("אחר");
		}},
		new ArrayList<String>() {{
			add("1");
			add("2");
			add("3");
		}},	TYPE_VALUE_STRING_LIST));
		
		//----------------- ddlHospital --------------------------------------------
		profileParamBlueForm.put("ddlHospital", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlHospital", "מוסד אם", "Text", new ArrayList<String>() {{
			add("אברבאנל");
			add("איכילוב");
			add("איתנים");
			add("אסף הרופא");
			add("ב. הנפש באר-שבע");
			add("באר-יעקב");
			add("ביח לוינשטיין");
			add("בילינסון");
			add("ביקור חולים");
			add("בית רבקה");
			add("בני - ציון");
			add("ברזילי");
			add("בריאות הנפש רמת חן");
			add("גהה");
			add("הדסה");
			add("הכרמל");
			add("הלל יפה");
			add("העמק");
			add("הרצפלד-גדרה");
			add("וולפסון");
			add("זיו צפת");
			add("טירת הכרמל");
			add("טלביה");
			add("יוספטל אילת");
			add("לב השרון");
			add("לשכת בריאות - אחר");
			add("לשכת בריאות אזור אשקלון");
			add("לשכת בריאות אילת");
			add("לשכת בריאות מחוז באר שבע");
			add("לשכת בריאות מחוז הצפון");
			add("מ.גריאטרי פרדס חנה");
			add("מאיר");
			add("מזרע");
			add("מרכז בריאות בקה");
			add("מרכז בריאות קרית שמונה");
			add("מרכז גר. נתניה");
			add("מרכז גר. רשלצ");
			add("נהריה");
			add("סורוקה");
			add("פוריה");
			add("פלימן חיפה");
			add("קמפ גולדה-השרון");
			add("קפלן");
			add("רמבם");
			add("שיבא");
			add("שלוותה");
			add("שמואל הרופא");
			add("שנידר");
			add("שער מנשה");
			add("שערי צדק");		}},
		new ArrayList<String>() {{
			add("1");
			add("2");
			add("3");
			add("4");
			add("5");
			add("6");
			add("7");
			add("8");
			add("9");
			add("10");
			add("11");
			add("12");
			add("13");
			add("14");
			add("15");
			add("16");
			add("17");
			add("18");
			add("19");
			add("20");
			add("21");
			add("22");
			add("23");
			add("24");
			add("25");
			add("26");
			add("49");
			add("50");
			add("48");
			add("47");
			add("27");
			add("28");
			add("29");
			add("30");
			add("31");
			add("32");
			add("33");
			add("34");
			add("35");
			add("36");
			add("37");
			add("38");
			add("39");
			add("40");
			add("41");
			add("42");
			add("43");
			add("44");
			add("45");
			add("46");
			}},	TYPE_VALUE_STRING_LIST));
		

		//----------------------- ddlProfession ------------------------------------
		profileParamBlueForm.put("ddlProfession", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlProfession", "מקצוע (אם מקצועך לא מופיע סמן/י אחר)", "הרדמה", new ArrayList<String>() {{
			add("הרדמה");
			add("ניאונטולוגיה");
			add("טיפול נמרץ ילדים");
			add("טיפול נמרץ כללי");
			add("טיפול נמרץ נשימתי");
			add("טיפול נמרץ כוויות");
			add("קרדיולוג בטיפול נמרץ לב");
			add("פנימית");
			add("גריאטריה");
			add("כירורגיה כללית");
			add("רפואה דחופה");
			add("פתולוגיה");
			add("המטו - אונקולוגיה ילדים");
			add("פיזקאלית ושיקום");
			add("פסיכיאטריה של הילד");
			add("רפואה גרעינית");
			add("כירורגית ילדים");
			add("כירורגית כלי דם");
			add("רנטגן");
			add("אחר");		}},
			new ArrayList<String>() {{
				add("1");
				add("2");
				add("3");
				add("4");
				add("5");
				add("6");
				add("7");
				add("8");
				add("9");
				add("10");
				add("11");
				add("12");
				add("13");
				add("14");
				add("15");
				add("16");
				add("17");
				add("18");
				add("19");
				add("20");
				}},	TYPE_VALUE_STRING_LIST));
			
		//--------------------- txtMisra -----------------------------------------
		profileParamBlueForm.put("txtMisra", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "txtMisra", "היקף משרה ($)","0",  
				null, null, TYPE_VALUE_INT));
		
	
		//---------------- Section 2 --------------------------------------
		
		//---------------- ddlMiktcoaMiuxad -------------------------------		 
		profileParamBlueForm.put("ddlMiktcoaMiuxad", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlMiktcoaMiuxad", "מקצועות מיוחדים (אם סימנת לעיל &quot;&quot;אחר&quot;&quot;)", "Boolean", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));

	
		//------------------------ ddlTosefetTfuka ----------------------------------
		profileParamBlueForm.put("ddlTosefetTfuka", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetTfuka", "תוספת תפוקה למומחים ומנהלים", "Boolean", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));
		
		//----------------------- ddlTosefetNihul ------------------------------------
		profileParamBlueForm.put("ddlTosefetNihul", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetNihul", "תוספת ניהול", "לא", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));

		//----------------------- ddlTosefetBriutTciburNIS -----------------------------
		profileParamBlueForm.put("ddlTosefetBriutTciburNIS", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetBriutTciburNIS", "תוספת בריאות הציבור (שקלית)", "לא", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));

		//----------------------- ddlTosefetBriutTciburPrecent ----------------------------
		profileParamBlueForm.put("ddlTosefetBriutTciburPrecent", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetBriutTciburPrecent", "וספת בריאות הציבור (אחוזית)", "לא", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));
		
		
		//----------------------- ddlGmulYeutc -------------------------------------------
		profileParamBlueForm.put("ddlGmulYeutc", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlGmulYeutc", "גמול ייעוץ", "לא", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));
		
		
		//---------------------- ddlTosefetRofim -----------------------------------------
		profileParamBlueForm.put("ddlTosefetRofim",  new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetRofim", "תוספת רופאים", "לא", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));
		
		
		//---------------------- ddlRofeMeshulav -----------------------------------------
		profileParamBlueForm.put("ddlRofeMeshulav", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlRofeMeshulav", "רופא משולב", "לא", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));
		
		//--------------------- ddlTosefetHinuchBriut -------------------------------------
		profileParamBlueForm.put("ddlTosefetHinuchBriut", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetHinuchBriut", "תוספת חינוך לבריאות (תחנות אם וילד ובתי ספר)", "לא", new ArrayList<String>() {{
			add("לא");
			add("כן");
		}},
		new ArrayList<String>() {{
			add("0");
			add("1");
		}},	TYPE_VALUE_BOOLEAN));
		 
		

		//----------------- Section 3 --------------------------------------------------------------
		
		//----------------------------txtHul----------------------------------------
		profileParamBlueForm.put("txtHul", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtHul", "מחלקה חול כמתמחה", "0", 
				null, null, TYPE_VALUE_UNIXTYPE));
		
		//----------------------------txtHulMetmahe----------------------------------
		profileParamBlueForm.put("txtHulMetmahe", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtHulMetmahe", "מחלקה חול כמומחה", "0",
				null, null, TYPE_VALUE_UNIXTYPE));

		//-----------------------------txtShishi--------------------------------------
		profileParamBlueForm.put("txtShishi", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtShishi", "מחלקה שישי/ערב חג כמתמחה", "0", 
				null, null, TYPE_VALUE_UNIXTYPE));

		//----------------------------txtShishiMetmahe--------------------------------
		profileParamBlueForm.put("txtShishiMetmahe", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtShishiMetmahe", "מחלקה שישי/ערב חג כמומחה", "0", 
				null, null, TYPE_VALUE_UNIXTYPE));
		
		//----------------------------txtShabat---------------------------------------
		profileParamBlueForm.put("txtShabat", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtShabat", "מחלקה שבת/חג כמתמחה", "0", 
				null, null, TYPE_VALUE_UNIXTYPE));
		
		//---------------------------txtShabatMetmahe----------------------------
		profileParamBlueForm.put("txtShabatMetmahe", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtShabatMetmahe", "מחלקה שבת/חג כמומחה",  "0", 
				null, null, TYPE_VALUE_UNIXTYPE));
		
		//---------------------------txtMiunHulReg-------------------------------
		profileParamBlueForm.put("txtMiunHulReg", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtMiunHulReg", "מיון חול כמתמחה",  "0", 
				null, null, TYPE_VALUE_UNIXTYPE));
		
		//--------------------------txtMiunHulRegMetmahe---------------------------
		profileParamBlueForm.put("txtMiunHulRegMetmahe", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtMiunHulRegMetmahe", "מיון חול כמומחה", "0",
				null, null, TYPE_VALUE_UNIXTYPE));

		//--------------------------txtMiunShishiReg-------------------------------
		profileParamBlueForm.put("txtMiunShishiReg", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtMiunShishiReg", "מיון שישי/ערב חג כמתמחה", "0",
				null, null, TYPE_VALUE_UNIXTYPE));
		
		//--------------------------txtMiunShishiRegMetmahe-----------------------
		profileParamBlueForm.put("txtMiunShishiRegMetmahe", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtMiunShishiRegMetmahe", "מיון שישי/ערב חג כמומחה", "0",
				null, null, TYPE_VALUE_UNIXTYPE));

		//--------------------------txtMiunShabatReg------------------------------
		profileParamBlueForm.put("txtMiunShabatReg", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtMiunShabatReg", "מיון שבת/חג כמתמחה", "0",
				null, null, TYPE_VALUE_UNIXTYPE));
		
		//--------------------------txtMiunShabatRegMetmahe----------------------
		profileParamBlueForm.put("txtMiunShabatRegMetmahe", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtMiunShabatRegMetmahe", "מיון שבת/חג כמומחה", "0",
				null, null, TYPE_VALUE_UNIXTYPE));
		
		
		
		//------------------------ Section 4--------------------------------------
		
		//-----------------------txtMahleketHul-------------------------------
		profileParamBlueForm.put("txtMahleketHul", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtMahleketHul", "מחלקה חול", 
				"0", null, null, TYPE_VALUE_UNIXTYPE));
		
		//-----------------------txtShishi2------------------------------------
		profileParamBlueForm.put("txtShishi2", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtShishi2", "מחלקה שישי/ערב חג", 
				"0", null, null, TYPE_VALUE_UNIXTYPE));
		
		//-----------------------txtShabat2-----------------------------------
		profileParamBlueForm.put("txtShabat2", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtShabat2", "מחלקה שבת/חג", 
				"0", null, null, TYPE_VALUE_UNIXTYPE));
		
		//------------------------txtMiunHul2-------------------------------
		profileParamBlueForm.put("txtMiunHul2", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtMiunHul2", "מיון חול", 
				"0", null, null, TYPE_VALUE_UNIXTYPE));
		
		//-----------------------txtMiunShishi2-----------------------------
		profileParamBlueForm.put("txtMiunShishi2", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtMiunShishi2", "מיון שישי/ערב חג", 
				"0", null, null, TYPE_VALUE_UNIXTYPE));
		
		//-----------------------txtMiunShabat2--------------------------------
		profileParamBlueForm.put("txtMiunShabat2", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtMiunShabat2", "מיון שבת/חג",
				"0", null, null, TYPE_VALUE_UNIXTYPE));
		
		
		//------------------------- Section 5 ---------------------------------
		
		//------------------------- txtYoungShlishHul --------------------------
		profileParamBlueForm.put("txtYoungShlishHul", new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtYoungShlishHul", "מחלקה חול",
				"0", null, null, TYPE_VALUE_UNIXTYPE));

		
		//--------------------------- txtMahlakaHul3 --------------------------
		profileParamBlueForm.put("txtMahlakaHul3",  new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtMahlakaHul3", "מחלקה חול",
				"0", null, null, TYPE_VALUE_UNIXTYPE));
				
		//----------------------------txtShishi3-------------------------------
		profileParamBlueForm.put("txtShishi3",  new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtShishi3", "מחלקה שישי/ערב חג",
				"0", null, null, TYPE_VALUE_UNIXTYPE));
				
		//--------------------------txtShabat3-------------------------------
		profileParamBlueForm.put("txtShabat3", new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtShabat3", "מחלקה שבת/חג",
				"0", null, null, TYPE_VALUE_UNIXTYPE));
				
		//--------------------------txtMiunHul3------------------------------
		profileParamBlueForm.put("txtMiunHul3", new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtMiunHul3", "מיון חול",
				"0", null, null, TYPE_VALUE_UNIXTYPE));
				
		//------------------------txtMiunShishi3-----------------------------
		profileParamBlueForm.put("txtMiunShishi3", new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtMiunShishi3", "מיון שישי/ערב חג",
				"0", null, null, TYPE_VALUE_UNIXTYPE));

		//------------------------txtMiunShabat3------------------------------
		profileParamBlueForm.put("txtMiunShabat3", new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtMiunShabat3", "מיון שבת/חג",
				"0", null, null, TYPE_VALUE_UNIXTYPE));
				
		//------------------------txtMahlakaMumshu---------------------------
		profileParamBlueForm.put("txtMahlakaMumshu",  new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtMahlakaMumshu", "כוננויות מחלקה שממומשו",
				"0", null, null, TYPE_VALUE_UNIXTYPE));

		//----------------------txtBikurShabat-------------------------------
		profileParamBlueForm.put("txtBikurShabat",  new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtBikurShabat", "ביקורים בשבת",
				"0", null, null, TYPE_VALUE_UNIXTYPE));
		

		//--------------------- Section 6 ----------------------------------
		
		//-------------------- txtKonenutYom --------------------------------
		profileParamBlueForm.put("txtKonenutYom", new ProfileParam(SECTION_6, Constants.SCREEN_ONE, "txtKonenutYom", "כוננות יום אחד",
				"0", null, null, TYPE_VALUE_UNIXTYPE));

		//---------------------txtYameiErex-----------------------------------
		profileParamBlueForm.put("txtYameiErex", new ProfileParam(SECTION_6, Constants.SCREEN_ONE, "txtYameiErex", "11 ימי ערך",
				"0", null, null, TYPE_VALUE_UNIXTYPE));
				
		
		//--------------------- Section 7 -------------------------------------

		//-----------------txtKonenyetAl------------------------------
		profileParamBlueForm.put("txtKonenyetAl", new ProfileParam(SECTION_7, Constants.SCREEN_ONE, "txtKonenyetAl", "כוננויות על",
				"0", null, null, TYPE_VALUE_UNIXTYPE));

		//--------------------txtKonenytMenahel-------------------------
		profileParamBlueForm.put("txtKonenytMenahel", new ProfileParam(SECTION_7, Constants.SCREEN_ONE, "txtKonenytMenahel", "כוננות מנהל בית חולים**",
				"0", null, null, TYPE_VALUE_UNIXTYPE));

		
		//--------------------- Section 8 -------------------------------------
		
		//---------------------------txtYoungMahlaka------------------------
		profileParamBlueForm.put("txtYoungMahlaka", new ProfileParam(SECTION_8, Constants.SCREEN_ONE, "txtYoungMahlaka", "מחלקה חול",
				"0", null, null, TYPE_VALUE_UNIXTYPE));

		//--------------------------txtYoungMiun----------------------------
		profileParamBlueForm.put("txtYoungMiun", new ProfileParam(SECTION_8, Constants.SCREEN_ONE, "txtYoungMiun", "מיון חול",
				"0", null, null, TYPE_VALUE_UNIXTYPE));

		
		//------------------------ Section 9 ---------------------------------

		//----------------------- txtNosafot125 -------------------------
		profileParamBlueForm.put("txtNosafot125", new ProfileParam(SECTION_9, Constants.SCREEN_ONE, "txtNosafot125", "שעות לפי 125%",
				"0", null, null, TYPE_VALUE_INT));

		//--------------------------txtNosafot150------------------------------
		profileParamBlueForm.put("txtNosafot150", new ProfileParam(SECTION_9, Constants.SCREEN_ONE, "txtNosafot150", "שעות לפי 150%",
				"0", null, null, TYPE_VALUE_INT));
	
		
		/*
		 * 
		 * 	Green Form
		 * 
		 */

		//--- Section 1 ----------------------------------------------------
		
		//---------------------- ddlDarga ---------------------------
		profileParamGreenForm.put("ddlDarga", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlDarga", "דרגה", "2", new ArrayList<String>() {{
			add("2");
			add("3");
			add("3.5");
			add("4");
			add("5");
			add("6");
			add("6.5");
			add("7");
			add("7.5");
			add("8");
			add("8.5");
			add("9");
			add("9.5");
			add("10");
			add("10.5");
			add("11");
			add("11.5");
			add("12");
			add("12.5");
		}},
		
		new ArrayList<String>() {{
			add("2");
			add("3");
			add("3.5");
			add("4");
			add("5");
			add("6");
			add("6.5");
			add("7");
			add("7.5");
			add("8");
			add("8.5");
			add("9");
			add("9.5");
			add("10");
			add("10.5");
			add("11");
			add("11.5");
			add("12");
			add("12.5");
		}},
		TYPE_VALUE_FLOAT_LIST));
			
		
		//--------------txtVetek----------------------
		profileParamGreenForm.put("txtVetek", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "txtVetek", "ותק לתשלום", "0",
				null, null, TYPE_VALUE_INT));

		
		//----------ddlMaamad-------------------------
		profileParamGreenForm.put("ddlMaamad", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlMaamad", "מעמד", "רופא שאינו מומחה",
				new ArrayList<String>() {{
					add("רופא שאינו מומחה");
					add("מומחה");
					add("מנהל");					
				}},
				new ArrayList<String>() {{
					add("1");
					add("2");
					add("3");
				}},
				TYPE_VALUE_STRING_LIST));

		
		//-----------ddlFamilyChild-----------------
		profileParamGreenForm.put("ddlFamilyChild", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlFamilyChild", "ילדים/משפחה", "משפחה",
				new ArrayList<String>() {{
					add("משפחה");
					add("ילדים");
				}},
				new ArrayList<String>() {{
					add("1");
					add("2");
				}},
				TYPE_VALUE_STRING_LIST));
		
		//----------ddlProfession---------------------
		profileParamGreenForm.put("ddlProfession", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlProfession", "מקצוע", "גריאטריה",
				new ArrayList<String>() {{
					add("גריאטריה");
					add("התפתחות הילד");
					add("אחר");
				}},
				new ArrayList<String>() {{
					add("2");
					add("6");
					add("16");
				}},
				TYPE_VALUE_STRING_LIST));
		
		//---------------ddlShaotMishmeret---------------
		profileParamGreenForm.put("ddlShaotMishmeret", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "ddlShaotMishmeret", "קיצור יום", "6",
				new ArrayList<String>() {{
					add("6");
					add("6.5");
					add("7");
					add("7.5");
					add("8");
				}},
				new ArrayList<String>() {{
					add("6");
					add("6.5");
					add("7");
					add("7.5");
					add("8");
				}},
				TYPE_VALUE_FLOAT_LIST));
		//----------------------txtMisra------------------------
		profileParamGreenForm.put("txtMisra", new ProfileParam(SECTION_1, Constants.SCREEN_TWO, "txtMisra", "היקף משרה(%)", "0",
				null, null, TYPE_VALUE_INT));

		
		//----------- Section 2 -----------------------------------

		//-------------------------ddlMinahelMirpaa----------------------
		profileParamGreenForm.put("ddlMinahelMirpaa", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlMinahelMirpaa", "אינדיקציית מנהל מרפאה", "לא",
			new ArrayList<String>() {{
				add("לא");
				add("כן");
			}},
			new ArrayList<String>() {{
				add("0");
				add("1");
			}},	TYPE_VALUE_BOOLEAN));

		//---------------------------ddlGodelMirpaa----------------------
		profileParamGreenForm.put("ddlGodelMirpaa", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlGodelMirpaa", "סיווג מרפאה", "שירות",
				new ArrayList<String>() {{
					add("שירות");
					add("קטנה");
					add("בינונית");
					add("גדולה");
				}},
				new ArrayList<String>() {{
					add("1");
					add("2");
					add("3");
					add("4");
				}},
				TYPE_VALUE_STRING_LIST));
				
				
		//-------------------------ddlTosefetMetmahe--------------------
		profileParamGreenForm.put("ddlTosefetMetmahe", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetMetmahe", "תוספת מתמחה", "לא",
				new ArrayList<String>() {{
					add("לא");
					add("כן");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));
		
		//-------------------------ddlTosefetShaotBeHeskem---------------
		profileParamGreenForm.put("ddlTosefetShaotBeHeskem", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetShaotBeHeskem", "תוספת שעות בהסכם", "לא",
				new ArrayList<String>() {{
					add("לא");
					add("כן");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));
				

		//---------------------ddlTosefetPerefiria---------------------
		profileParamGreenForm.put("ddlTosefetPerefiria", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetPerefiria", "תוספת פריפריה","לא",
				new ArrayList<String>() {{
					add("לא");
					add("כן");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));
				
		
		//--------------ddlTosefetNihyul---------------------------------
		profileParamGreenForm.put("ddlTosefetNihyul", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlTosefetNihyul", "תוספת ניהול", "לא",
				new ArrayList<String>() {{
					add("לא");
					add("כן");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));
				
		//--------------ddlMenahelMahlakaFamily---------------------------
		profileParamGreenForm.put("ddlMenahelMahlakaFamily", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlMenahelMahlakaFamily", "אינדיקציית מנהל מחלקה (משפחה)", "לא",
				new ArrayList<String>() {{
					add("לא");
					add("כן");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));
				
		//--------------ddlBriutEled---------------------------------------
		profileParamGreenForm.put("ddlBriutEled", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlBriutEled", "אינדיקציית מרכז בריאות הילד", "לא",
				new ArrayList<String>() {{
					add("לא");
					add("כן");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));
						
		//--------------ddlHeskemKhila---------------------------------------
		profileParamGreenForm.put("ddlHeskemKhila", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlHeskemKhila", "הצטרף להסכם קהילה", "לא",
				new ArrayList<String>() {{
					add("לא");
					add("כן");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));
				
		//-----------------ddlMisparPitculimKavya-----------------------------
		profileParamGreenForm.put("ddlMisparPitculimKavya", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "ddlMisparPitculimKavya", "מספר פיצולים קבוע שבועי (סמל 1211)", "0",
				new ArrayList<String>() {{
					add("0");
					add("1");
					add("2");
					add("3");
					add("4");
					add("5");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
					add("2");
					add("3");
					add("4");
					add("5");
				}},
				TYPE_VALUE_INT_LIST));
				
		//-----------------txtMisparPitculimBePoal-----------------------------
		profileParamGreenForm.put("txtMisparPitculimBePoal", new ProfileParam(SECTION_2, Constants.SCREEN_TWO, "txtMisparPitculimBePoal", "מספר פיצולים בפועל חודשי (סמל 1262)", "0",
				null, null, TYPE_VALUE_INT));
		
		
		
		//------ Section 3 ---------------------------------
		
		//--------------------ddlTosefetRofeKfar-------------------------------
		profileParamGreenForm.put("ddlTosefetRofeKfar", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "ddlTosefetRofeKfar", "האם רופא כפר?", "לא",
				new ArrayList<String>() {{
					add("לא");
					add("כן");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));

		//-------------------txtMisparKfari-------------------------------
		profileParamGreenForm.put("txtMisparKfari", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtMisparKfari", "מס' כפרים", "0",
				null, null, TYPE_VALUE_INT));

		//---------------txtVetekBeKfar-----------------------------------
		profileParamGreenForm.put("txtVetekBeKfar", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtVetekBeKfar", "ותק בכפר", "0",
				null, null, TYPE_VALUE_INT));
		
		//---------------txtHalkiutMisraBeKfar-------------------------------
		profileParamGreenForm.put("txtHalkiutMisraBeKfar", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "txtHalkiutMisraBeKfar", "חלקיות משרה בכפר (%)", "0",
				null, null, TYPE_VALUE_INT));
				
		//-------------ddlMegorim----------------------------------------------
		profileParamGreenForm.put("ddlMegorim", new ProfileParam(SECTION_3, Constants.SCREEN_ONE, "ddlMegorim", "מגורים", "כפר", 
				new ArrayList<String>() {{
					add("כפר");
					add("עיר");
				}},
				new ArrayList<String>() {{
					add("0");
					add("1");
				}},	TYPE_VALUE_BOOLEAN));

		
		//------------------------ Section 4 -------------------------------------
		
		//-------------txtHefashotNominaliot-------------------------------------
		profileParamGreenForm.put("txtHefashotNominaliot", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtHefashotNominaliot", "נפשות נומינאליות", "0", 
				null, null, TYPE_VALUE_INT));

		//------------txtTosefetHefashotRagil----------------------------------
		profileParamGreenForm.put("txtTosefetHefashotRagil", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtTosefetHefashotRagil", "תוספת נפשות - גיל (סה\"\"כ)", "0",
				null, null, TYPE_VALUE_INT));
		
		//-----------txtTosefetHefashotTehula-------------------------------------
		profileParamGreenForm.put("txtTosefetHefashotTehula", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtTosefetHefashotTehula", "תוספת נפשות - תחלואה", "0",
				null, null, TYPE_VALUE_INT));

		//------------txtNefashotMealGil65---------------------------------------
		profileParamGreenForm.put("txtNefashotMealGil65", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtNefashotMealGil65", "נפשות מעל גיל 65", "0",
				null, null, TYPE_VALUE_INT));
		
		//-----------txtNaheiRadifut--------------------------------------------
		profileParamGreenForm.put("txtNaheiRadifut", new ProfileParam(SECTION_4, Constants.SCREEN_ONE, "txtNaheiRadifut", "כמות נכי רדיפות", "0",
				null, null, TYPE_VALUE_INT));
		
		//--------------- Section 5 --------------------------------------
		
		//----------------txtRofeNilve------------------------------------
		profileParamGreenForm.put("txtRofeNilve", new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtRofeNilve", "כמות רופא נלווה", "0",
				null, null, TYPE_VALUE_INT));
				
		//---------------txtPitkeiAmit-----------------------------------
		profileParamGreenForm.put("txtPitkeiAmit", new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtPitkeiAmit", "כמות פתקי עמית (נפשות החלפה)", "0",
				null, null, TYPE_VALUE_INT));
		
		//---------------txtMiud-------------------------------------------
		profileParamGreenForm.put("txtMiud", new ProfileParam(SECTION_5, Constants.SCREEN_ONE, "txtMiud", "כמות מיעודים", "0",
				null, null, TYPE_VALUE_INT));
		
		//--------------------- Section 6 ----------------------------------
		
		//---------------------txt1080--------------------------------------
		profileParamGreenForm.put("txt1080", new ProfileParam(SECTION_6, Constants.SCREEN_ONE, "txt1080", "בתוך האיזור - לא יזום (סמל 1080)", "0",
				null, null, TYPE_VALUE_UNIXTYPE));

		//-------------txt1090-----------------------------------------------
		profileParamGreenForm.put("txt1090", new ProfileParam(SECTION_6, Constants.SCREEN_ONE, "txt1090", "בתוך האיזור - יזום (סמל 1090)", "0",
				null, null, TYPE_VALUE_UNIXTYPE));
		
		//-----------txt1095-------------------------------------------------
		profileParamGreenForm.put("txt1095", new ProfileParam(SECTION_6, Constants.SCREEN_ONE, "txt1095", "מחוץ לאיזור - לא יזום (סמל 1095)", "0",
				null, null, TYPE_VALUE_UNIXTYPE));

		//-------------txt1096-----------------------------------------------
		profileParamGreenForm.put("txt1096", new ProfileParam(SECTION_6, Constants.SCREEN_ONE, "txt1096", "מחוץ לאיזור - יזום (סמל 1096)", "0",
				null, null, TYPE_VALUE_UNIXTYPE));
		
		
		//---------------------- Section 7 ----------------------------------

		//----------------------txtTosefetRofeEizori------------------------
		profileParamGreenForm.put("txtTosefetRofeEizori", new ProfileParam(SECTION_7, Constants.SCREEN_ONE, "txtTosefetRofeEizori", "תוספת רופא איזורי", "0",
				null, null, TYPE_VALUE_UNIXTYPE));

		//---------------------txtTosefetMenahelMaxlaka------------------------
		profileParamGreenForm.put("txtTosefetMenahelMaxlaka", new ProfileParam(SECTION_7, Constants.SCREEN_ONE, "txtTosefetMenahelMaxlaka", "תוספת מנהל מחלקה", "0",
				null, null, TYPE_VALUE_UNIXTYPE));

		//---------------------txt1126---------------------------------------
		profileParamGreenForm.put("txt1126", new ProfileParam(SECTION_7, Constants.SCREEN_ONE, "txt1126", "שעות תורנות (סמל 1126)","0",
				null, null, TYPE_VALUE_UNIXTYPE));

		//------------------------- Section 8 --------------------------------
		
		//--------------------------txt125-----------------------------------
		profileParamGreenForm.put("txt125", new ProfileParam(SECTION_8, Constants.SCREEN_ONE, "txt125", "שעות לפי 125%", "0",
				null, null, TYPE_VALUE_INT));
				
		//--------------------------txt150------------------------------------
		profileParamGreenForm.put("txt150", new ProfileParam(SECTION_8, Constants.SCREEN_ONE, "txt150", "שעות לפ 150%", "0",
				null, null, TYPE_VALUE_INT));
				
				
	}

	
	
	//---------------------------------------------------------------------

	
	private String getDate(long time) {
	    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
	    cal.setTimeInMillis(time*1000);
	    String date = DateFormat.format("dd-MM-yyyy", cal).toString();
	    return date;
	}


	
	//---------------------------------------------------------------------
	
	int global_idx=0;
	class ProfileHashMap extends HashMap<String, ProfileParam>{
		public ProfileParam put(String key, ProfileParam value){
			((ProfileParam)(value)).idx = global_idx++;	// For sort
			super.put(key, value);
			return value;
		}
	}


	// Two Form - Blue Form
	private HashMap<String, ProfileParam> profileParamBlueForm = new ProfileHashMap();

	// One Form - Green Form
	private HashMap<String, ProfileParam> profileParamGreenForm = new ProfileHashMap();

	/*
	 * 
	 * Get Form	
	 * 
	 */
	public HashMap<String, ProfileParam> getProfileParams(int form){
		if(form == Constants.FORM_BLUE)
			return profileParamBlueForm;
		else if(form == Constants.FORM_GREEN)
			return profileParamGreenForm;
		else
		return null;
	}

	public String getValue(String key, int form){
		return getProfileParams(form).get(key).value;
	}

	public boolean setValue(String key, String value, int form){
		ProfileParam param = getProfileParams(form).get(key);

		switch (param.typeValue) {
		case TYPE_VALUE_BOOLEAN:
			// Value
			boolean v = Boolean.valueOf(value);
			param.serverValue = v ? "true" : "false";
			// Label
			param.value = v ? param.labelList.get(1) : param.labelList.get(0);
			break;
			
		case TYPE_VALUE_FLOAT_LIST:
			float fParamVar = Float.valueOf(value);
			for(int i=0; i< param.valueList.size(); i++){
				float fVal = Float.valueOf(param.valueList.get(i));
				if(fVal == fParamVar){
					param.value = param.labelList.get(i);
					param.serverValue = param.valueList.get(i);
					break;
				}
			}
			break;
			
		case TYPE_VALUE_INT_LIST:
		case TYPE_VALUE_STRING_LIST:
			int idx = param.valueList.indexOf(value);
			//System.out.println( "idx=" + idx + " : value=" + value );
			param.value = param.labelList.get(idx);
			param.serverValue = param.valueList.get(idx);
			break;
			
		case TYPE_VALUE_INT:
		case TYPE_VALUE_FLOAT:
			param.serverValue = param.value = value;
			break;

		case TYPE_VALUE_UNIXTYPE:
			try {
				param.unixTimeList = new ArrayList<String>();
				JSONObject jObj = new JSONObject(value);
				param.value = value;
		    	String unixTimeValue = jObj.getString("Value");
		    	JSONArray jDates = jObj.getJSONArray("Dates");
		    	for(int i=0; i < jDates.length(); i ++){
		    		long unixTime = jDates.getLong(i);
		    		String date = getDate(unixTime);
		    		param.unixTimeList.add(date);
		    	}
				param.value = String.valueOf(jDates.length());
				param.serverValue = param.value;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return false;
	}


	/*
	 * 
	 * 	Update Values before sending to server
	 * 
	 * 
	 */
	public void setValueForServer(Social socialItem, ProfileParam param) {

		switch (socialItem.getTypeValue()) {
		case TYPE_VALUE_BOOLEAN:
			boolean v = Boolean.valueOf(socialItem.getValue());
			// Value
			param.serverValue = v ? "true" : "false";
			// Labels
			param.value = v ? param.labelList.get(1) : param.labelList.get(0);
			break;
		case TYPE_VALUE_STRING_LIST:
			String strVal = socialItem.getValue();
			int idx = param.labelList.indexOf(strVal);
			param.value = param.labelList.get(idx);
			param.serverValue = param.valueList.get(idx);
			break;
		case TYPE_VALUE_FLOAT_LIST:
			float fParamVar = Float.valueOf(socialItem.getValue());
			for(int i=0; i< param.valueList.size(); i++){
				float fVal = Float.valueOf(param.valueList.get(i));
				if(fVal == fParamVar){
					param.value = param.labelList.get(i);
					param.serverValue = param.valueList.get(i);
					break;
				}
			}
			break;
		case TYPE_VALUE_INT:
		case TYPE_VALUE_FLOAT:
			param.serverValue = param.value = socialItem.getValue();
			break;
		case TYPE_VALUE_UNIXTYPE:
			param.serverValue = "0";
			if(socialItem.getUnixTimeList() != null && socialItem.getUnixTimeList().size() > 0){
				param.serverValue = param.value = String.valueOf(socialItem.getUnixTimeList().size());
		    	param.unixTimeList.clear();
		    	param.unixTimeList.addAll(socialItem.getUnixTimeList());
			}
			break;
		default:
			break;
		}

		}
	
	
	//------------------------------------ Result Form------------------------------------------------------------
	
	public class ResultForm{
		public static final int TYPE_GROUP = 0;
		public static final int TYPE_CHILD = 1;
		public static final int TYPE_GROUP_HEADER = 2;
		public String title;
		public String value;
		public int color;
		public ArrayList<ResultForm> sections = new ArrayList<ResultForm>();
		public ResultForm(String title) {
		this.title = title;
		this.color = 0;
		}
		public void setValue(double value) {
			this.value = String.format("%.2f", value);
		}
		public void setValue(String value) {
			this.value = value;
		}
	}


	public ArrayList<ResultForm> getResultForm(int form){
		if(form == Constants.FORM_GREEN)
			return resultOneForm;
		else 
			return resultTwoForm;
	}


	ArrayList<ResultForm> resultOneForm = new ArrayList<ResultForm>();

	ArrayList<ResultForm> resultTwoForm = new ArrayList<ResultForm>();


	void initResultOneForm(){

			resultOneForm.add(new ResultForm("שכר משולב"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("שכר משולב"));
			resultOneForm.add(new ResultForm("שקליות"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת שקלית 2012"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת מקצועות מיוחדים"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת אקוטית 2012"));
			resultOneForm.add(new ResultForm("אחוזיות"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת מתמחה"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת מקצועות במצוקה 2012"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת פריפריה"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת ניהול"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("סה&quote;כ תוספות קבועות לערך יום"));
			resultOneForm.add(new ResultForm("שקליות"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת 2000"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת שעות בהסכם"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת אקוטית"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת רופא כפר"));
			resultOneForm.add(new ResultForm("אחוזיות"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת משלימה 2012"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת מינהל מרפאה"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת פיצול קבועה (סמל 1211)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת פיצול משתנה (סמל 1262)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("סה&quote;כ תוספות קבועות לא לערך יום"));
			resultOneForm.add(new ResultForm("ערך יום - לצורך חישוב עבודה נוספת"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("שעות לתשלום (לחישוב ערך שעה)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("ערך יום"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("ערך שעה"));
			resultOneForm.add(new ResultForm("נורמה"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תשלום נפשות מעל לנורמה"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("נפשות מעל גיל 65 א' (סמל 832)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("נפשות מעל גיל 65 ב' (סמל 1616)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("נכה רדיפות"));
			resultOneForm.add(new ResultForm("עבודה נוספת"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("רופא נלווה"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("פתקי עמית (נפשות החלפה)(נפשות החלפה)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("פתקי עמית למרכז בריאות הילד"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("מיעוד"));
			resultOneForm.add(new ResultForm("ביקורי בית"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("בתוך האיזור - לא יזום(סמל 1080)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("בתוך האיזור - יזום (סמל 1090)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("מחוץ לאיזור - לא יזום (סמל 1095)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("מחוץ לאיזור - יזום (סמל 1096)"));
			resultOneForm.add(new ResultForm("כוננויות ותורנויות"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת רופא איזורי"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת מנהל מחלקה"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("שעות תורנות (סמל 1126)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("כוננות כפר לרופאים"));
			resultOneForm.add(new ResultForm("שעות נוספות"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("שעות לפי 125%"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("שעות לפי 150%"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("סה&quote;כ עבודה נוספת"));
			resultOneForm.add(new ResultForm("מאפייני הרופא"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("איזור"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("סה&quote;כ נפשות לנורמה (משוקללות)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("נורמה"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("נפשות מעל לנורמה"));
			resultOneForm.add(new ResultForm("זכאויות לפי הסימולאטור (למידע בלבד)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספת שעות בגין נפשות (לשבוע)"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("מספר כוננויות רופא אזורי"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("מס' כוננויות מנהל מחלקה במשפחה"));
			resultOneForm.add(new ResultForm("השתכרות"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("שכר משולב"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("תוספות קבועות"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("עבודה נוספת"));
			resultOneForm.get(resultOneForm.size()-1).sections.add(new ResultForm("סה&quote;כ השתכרות ברוטו*"));
		}


	void initResultTwoForm(){

		resultTwoForm.add(new ResultForm("שכר משולב"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("שכר משולב"));
		resultTwoForm.add(new ResultForm("שקליות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת שקלית 2012"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת מקצועות מיוחדים"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת מקצועות אקוטיים"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת מקצועות במצוקה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("בריאות הציבור"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("פריפריה א'"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת שילוב"));
		resultTwoForm.add(new ResultForm("אחוזיות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת מתמחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("פריפריה אחוזית"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("פריפריה אחוזית - בריאות הציבור"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת ניהול"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("סה&quote;כ תוספות קבועות לערך יום"));
		resultTwoForm.add(new ResultForm("שקליות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת 2000"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת תפוקה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת מקצועות אקוטיים"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("פריפריה ב'"));
		resultTwoForm.add(new ResultForm("אחוזיות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("גמול ייעוץ"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("בריאות הציבור"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת רופאים"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת חינוך לבריאות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("סה&quote;כ תוספות קבועות לא לערך יום"));
		resultTwoForm.add(new ResultForm("ערך יום - לצורך חישוב עבודה נוספת"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("ערך יום"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("ערך שעה לתשלום שעות נוספות"));
		resultTwoForm.add(new ResultForm("עבודה נוספת"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("שעות נוספות גלובאליות"));
		resultTwoForm.add(new ResultForm("תורנויות מלאות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה חול כמומחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה חול כמתמחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה שישי/ערב חג כמומחה "));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה שישי/ערב חג כמתמחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה שבת/חג  כמומחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה שבת/חג כמתמחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון חול כמומחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון חול כמתמחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון שישי/ערב חג כמומחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון שישי/ערב חג כמתמחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון שבת/חג כמומחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון שבת/חג כמתמחה"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספת ממוצעת בגין 5 תורנויות ומעלה"));
		resultTwoForm.add(new ResultForm("חצאי תורנויות למומחים ומנהלים"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה חול"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה שישי/ערב חג"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה שבת/חג"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון חול"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון שישי/ערב חג"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון שבת/חג"));
		resultTwoForm.add(new ResultForm("שליש תורנויות למומחים צעירים"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה חול"));
		resultTwoForm.add(new ResultForm("כוננויות מתוכננות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה חול"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה שישי"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה שבת"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון חול"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון שישי"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון שבת"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("כוננויות מחלקה שממומשו"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("ביקורים בשבת"));
		resultTwoForm.add(new ResultForm("כונניויות רופאי מינהל"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("כוננות יום אחד"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("11 ימי ערך"));
		resultTwoForm.add(new ResultForm("כונניויות מנהלים"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("כוננויות על"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("כוננות מנהל בית חולים"));
		resultTwoForm.add(new ResultForm("מודל כוננויות למומחים צעירים בגין התמדה (בונוס)"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מחלקה חול"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("מיון חול"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("סה&quote;כ עבודה נוספת"));
		resultTwoForm.add(new ResultForm("מאפייני הרופא"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("סיווג המקצוע:"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("איזור:"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("ערך ממוצע של תורנות:"));
		resultTwoForm.add(new ResultForm("השתכרות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("שכר משולב"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("תוספות קבועות"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("עבודה נוספת"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("שעות נוספות לפי 125%"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("שעות נוספות לפי 150%"));
		resultTwoForm.get(resultTwoForm.size()-1).sections.add(new ResultForm("סה&quote;כ השתכרות ברוטו*"));
	}

	
}

