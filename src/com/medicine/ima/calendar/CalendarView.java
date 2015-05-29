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

import com.medicine.ima.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.MonthDisplayHelper;
import android.view.MotionEvent;
import android.widget.ImageView;

public class CalendarView extends ImageView {
    private static int WEEK_TOP_MARGIN = 74;
    private static int WEEK_LEFT_MARGIN = 40;
    private static int CELL_WIDTH = 58;
    private static int CELL_HEIGH = 53;
    private static int CELL_MARGIN_TOP = 92;
    private static int CELL_MARGIN_LEFT = 39;
    private static float CELL_TEXT_SIZE;

    private static int CELL_SELECT_SHIFT = 2;
    
	private static final String TAG = "CalendarView"; 
	private Calendar mRightNow = null;
    private Drawable mWeekTitle = null;
    private Cell mToday = null;
    private Cell[][] mCells = new Cell[6][7];
    private OnCellTouchListener mOnCellTouchListener = null;
    MonthDisplayHelper mHelper;
    Bitmap mSelectedDecoration = null;
	private Context context;


	public interface OnCellTouchListener {
    	public void onTouch(Cell cell);
    }

	public CalendarView(Context context) {
		this(context, null);
	}
	
	public CalendarView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		mSelectedDecoration = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.calendar_day_selected);
		initCalendarView();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(2);		
		mPaint.setColor(Color.BLUE);

	}
	
	private void initCalendarView() {
		mRightNow = Calendar.getInstance();
		// prepare static vars
		Resources res = getResources();
		WEEK_TOP_MARGIN  = (int) res.getDimension(R.dimen.week_top_margin);
		WEEK_LEFT_MARGIN = (int) res.getDimension(R.dimen.week_left_margin);
		
		CELL_WIDTH = (int) res.getDimension(R.dimen.cell_width);
		CELL_HEIGH = (int) res.getDimension(R.dimen.cell_heigh);
		CELL_MARGIN_TOP = (int) res.getDimension(R.dimen.cell_margin_top);
		CELL_MARGIN_LEFT = (int) res.getDimension(R.dimen.cell_margin_left);

		CELL_TEXT_SIZE = res.getDimension(R.dimen.cell_text_size);
		// set background
		setImageResource(R.drawable.calendar_background);
		mWeekTitle = res.getDrawable(R.drawable.calendar_week);
		
		mHelper = new MonthDisplayHelper(mRightNow.get(Calendar.YEAR), mRightNow.get(Calendar.MONTH));
    }

    class _calendar {
    	public int day;
    	public boolean thisMonth;
    	public _calendar(int d, boolean b) {
    		day = d;
    		thisMonth = b;
    	}
    	public _calendar(int d) {
    		this(d, false);
    	}
    };
    _calendar tmp[][] = new _calendar[6][7];
	private Paint mPaint;
	
	private void initCells() {
	    
	    for(int i=0; i<tmp.length; i++) {
	    	int n[] = mHelper.getDigitsForRow(i);
	    	for(int d=0; d<n.length; d++) {
	    		if(mHelper.isWithinCurrentMonth(i,d))
	    			tmp[i][d] = new _calendar(n[d], true);
	    		else
	    			tmp[i][d] = new _calendar(n[d]);
	    	}
	    }

	    Calendar today = Calendar.getInstance();
	    int thisDay = 0;
	    mToday = null;
	    if(mHelper.getYear()==today.get(Calendar.YEAR) && mHelper.getMonth()==today.get(Calendar.MONTH)) {
	    	thisDay = today.get(Calendar.DAY_OF_MONTH);
	    }
		// build cells
		Rect Bound = new Rect(CELL_MARGIN_LEFT, CELL_MARGIN_TOP, CELL_WIDTH+CELL_MARGIN_LEFT, CELL_HEIGH+CELL_MARGIN_TOP);
		for(int week=0; week<mCells.length; week++) {
			for(int day=0; day<mCells[week].length; day++) {
				if(tmp[week][day].thisMonth) {
					if(day==0 || day==6 )
						mCells[week][day] = new RedCell(tmp[week][day].day, new Rect(Bound), CELL_TEXT_SIZE);
					else 
						mCells[week][day] = new Cell(tmp[week][day].day, new Rect(Bound), CELL_TEXT_SIZE);
				} else {
					mCells[week][day] = new GrayCell(tmp[week][day].day, new Rect(Bound), CELL_TEXT_SIZE);
				}
				
				Bound.offset(CELL_WIDTH, 0); // move to next column
				
				// get today
				if(tmp[week][day].day==thisDay && tmp[week][day].thisMonth) {
					mToday = mCells[week][day];
				}
			}
			Bound.offset(0, CELL_HEIGH); // move to next row and first column
			Bound.left = CELL_MARGIN_LEFT;
			Bound.right = CELL_MARGIN_LEFT+CELL_WIDTH;
		}		
	}
	
	
	
	void selectCell(Cell cell){
		//mDecoration.setBounds(cell.getBound().left + CELL_SELECT_SHIFT, cell.getBound().top + CELL_SELECT_SHIFT, 
		//		cell.getBound().right + CELL_SELECT_SHIFT, cell.getBound().bottom + CELL_SELECT_SHIFT );
	}


	@Override
	public void onLayout(boolean changed, int left, int top, int right, int bottom) {
		Rect re = getDrawable().getBounds();
		WEEK_LEFT_MARGIN = CELL_MARGIN_LEFT = (right-left - re.width()) / 2;
		mWeekTitle.setBounds(WEEK_LEFT_MARGIN, WEEK_TOP_MARGIN, WEEK_LEFT_MARGIN+mWeekTitle.getMinimumWidth(), WEEK_TOP_MARGIN+mWeekTitle.getMinimumHeight());
		initCells();
		super.onLayout(changed, left, top, right, bottom);
	}
	
    public void setTimeInMillis(long milliseconds) {
    	mRightNow.setTimeInMillis(milliseconds);
    	initCells();
    	this.invalidate();
    }
        
    public int getYear() {
    	return mHelper.getYear();
    }
    
    public int getMonth() {
    	return mHelper.getMonth();
    }
    
    public void nextMonth() {
    	mHelper.nextMonth();
    	initCells();
    	invalidate();
    }
    
    public void previousMonth() {
    	mHelper.previousMonth();
    	initCells();
    	invalidate();
    }
    
    public boolean firstDay(int day) {
    	return day==1;
    }
    
    public boolean lastDay(int day) {
    	return mHelper.getNumberOfDaysInMonth()==day;
    }
    
    public void goToday() {
    	Calendar cal = Calendar.getInstance();
    	mHelper = new MonthDisplayHelper(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH));
    	initCells();
    	invalidate();
    }
    
    public Calendar getDate() {
    	return mRightNow;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if(mOnCellTouchListener!=null){
	    	for(Cell[] week : mCells) {
				for(Cell day : week) {
					if(day.hitTest((int)event.getX(), (int)event.getY())) {
						mOnCellTouchListener.onTouch(day);
					}						
				}
			}
    	}
    	return super.onTouchEvent(event);
    }
  
    public void setOnCellTouchListener(OnCellTouchListener p) {
		mOnCellTouchListener = p;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// draw background
		super.onDraw(canvas);
		mWeekTitle.draw(canvas);
		
		// draw cells
		for(Cell[] week : mCells) {
			for(Cell day : week) {
				day.draw(canvas);
			}
		}

    	Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        if(strDatesArray != null){
	        for(String strDate : strDatesArray){
	            try {
	            	calendar.setTime(sdf.parse(strDate));
	            	int day = calendar.get(Calendar.DATE) % 7;
	            	int week = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) - 1;
					//canvas.drawBitmap(mSelectedDecoration, mCells[week][day].getBound().left, 
					//		mCells[week][day].getBound().top, null);

					canvas.drawCircle(mCells[week][day].getBound().exactCenterX() + CELL_SELECT_SHIFT, 
							mCells[week][day].getBound().exactCenterY() + CELL_SELECT_SHIFT, dpToPix(16), mPaint);
					
	            } catch (ParseException e) {
					e.printStackTrace();
				}
	         }
        }

	}
	
	public class GrayCell extends Cell {
		public GrayCell(int dayOfMon, Rect rect, float s) {
			super(dayOfMon, rect, s);
			mPaint.setColor(Color.LTGRAY);
		}
	}

	private class RedCell extends Cell {
		public RedCell(int dayOfMon, Rect rect, float s) {
			super(dayOfMon, rect, s);
			mPaint.setColor(0xdddd0000);
		}			
		
	}

	ArrayList<String> getSelectedDatesList(){
		return strDatesArray;
	}

	ArrayList<String> strDatesArray = new ArrayList<String>();
	public void setDayList(ArrayList<String> strDatesArray) {
		this.strDatesArray.addAll(strDatesArray);
	}

	/*
	 *
	 * 		switch blue round
	 *
	 */
	public void switchSelectedDay(Cell cell) {
		int year  = getYear();
		int month = getMonth();
		int day   = cell.getDayOfMonth();

		if(cell instanceof Cell) {
	    	Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.DAY_OF_MONTH, day);
	        calendar.set(Calendar.MONTH, month);
	        calendar.set(Calendar.YEAR, year);
		    String strDateClicked = DateFormat.format("dd-MM-yyyy", calendar).toString();
			
			if(!this.strDatesArray.contains(strDateClicked))
				this.strDatesArray.add(strDateClicked);
			else
				this.strDatesArray.remove(strDateClicked);
		}
	}

	   int dpToPix(int dp){
		   DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		   return (int)((dp * displayMetrics.density) + 0.5);
	   }
	   
	   int pixToDp(int px){ 
		   DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		   return (int) ((px/displayMetrics.density)+0.5);
	   }

}



