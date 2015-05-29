package com.medicine.ima;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppSettings {

    //
    //
    //
        public static boolean isNetworkAvailable(Context context) {
    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    // 	if no network is available networkInfo will be null, otherwise check if we are connected
    	if (networkInfo != null && networkInfo.isConnected()) {
        	return true;
    	}
    	return false;
	}

        

        public static boolean inputErrorMessage(final Context context, boolean isCancelable){
    		final boolean isCancelableDialog = isCancelable;
		    AlertDialog alertDialog = new AlertDialog.Builder(context).create(); 
	        alertDialog.setTitle("Error");
    	    alertDialog.setMessage("Empty");
	        //alertDialog.setIcon(R.drawable.tick);
        	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	dialog.cancel();
                	if(isCancelableDialog)
                		((Activity)context).finish();
                }
        	});
        	alertDialog.show();
			return false;
			}
    
        
    public static boolean serviceErrorMessage(final Context context, boolean isCancelable){
    		final boolean isCancelableDialog = isCancelable;
		    AlertDialog alertDialog = new AlertDialog.Builder(context).create(); 
	        alertDialog.setTitle(context.getString(R.string.service_error_header));
    	    alertDialog.setMessage(context.getString(R.string.service_error_message));
	        //alertDialog.setIcon(R.drawable.tick);
        	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	dialog.cancel();
                	if(isCancelableDialog)
                		((Activity)context).finish();
                }
        	});
        	alertDialog.show();
			return false;
			}
    
   

}
