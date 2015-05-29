package com.medicine.ima.jsontasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
public class JSONPOSTParser {
	private String cookies;
	private JSONObject responseJson = null;
	String userAgent = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";
  
  
	// constructor
	public JSONPOSTParser(String cookies) {	
		this.cookies = cookies;
	}

  
  public JSONObject sendJSONToServer(String url, JSONObject obj) {

	  String strResponse = null;
	  DefaultHttpClient httpClient = new DefaultHttpClient();

	  if(cookies!=null){
		CookieStore cookieStore = httpClient.getCookieStore();
		BasicClientCookie cookie = new BasicClientCookie("sessiondata", cookies);
	    URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} 
		String domain = uri.getHost();
		cookie.setDomain(domain);
		
		cookieStore.addCookie(cookie);
		httpClient.setCookieStore(cookieStore);
		
		}
	  try {
		  	
	    	 HttpPost httpPost = new HttpPost(url.toString());
	    	 httpPost.setHeader("Content-type", "application/json");
	    	 httpPost.setHeader("User-Agent", userAgent);
	
	         StringEntity se = new StringEntity(obj.toString()); 
	         se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	         httpPost.setEntity(se); 
	
	         HttpResponse response = httpClient.execute(httpPost);
	         strResponse = EntityUtils.toString(response.getEntity());

			responseJson = new JSONObject(strResponse);

	  	} catch (ClientProtocolException e) {
	      Log.e("JSON POST Parser", "ClientProtocolException " + e.toString());

		} catch (JSONException e) {
		      Log.e("JSON POST Parser", "JSONException " + e.toString());
			
	  } catch (IOException e) {
	      Log.e("JSON POST Parser", "IOException " + e.toString());
	  }
	return responseJson;
    }
  
}
