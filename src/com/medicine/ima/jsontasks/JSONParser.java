package com.medicine.ima.jsontasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.webkit.CookieManager;
public class JSONParser {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private String cookies;
	String userAgent = "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36";

  // constructor
  public JSONParser(String cookies) {
	  this.cookies = cookies;
  }
  public JSONObject getJSONFromUrl(String url) {
    // Making HTTP request
    try {
        // defaultHttpClient
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
        
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", userAgent);
        
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        is = httpEntity.getContent();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(
          is, "UTF-8"), 8);
      StringBuilder sb = new StringBuilder();
      String line = null;
      while ((line = reader.readLine()) != null) {
        sb.append(line + "n");
      }
      is.close();
      json = sb.toString();
    } catch (Exception e) {
      Log.e("Buffer Error", "Error converting result " + e.toString());
    }
    // try parse the string to a JSON object
    try {
      jObj = new JSONObject(json);
    } catch (JSONException e) {
      Log.e("JSON Parser", "Error parsing data " + e.toString());
    }
    // return JSON String
    return jObj;
  }
  
  /*
  public static BasicCookieStore getCookieStore(String cookies, String domain) {
	    String[] cookieValues = cookies.split(";");
	    BasicCookieStore cs = new BasicCookieStore();

	    BasicClientCookie cookie;
	    for (int i = 0; i < cookieValues.length; i++) {
	        String[] split = cookieValues[i].split("=");
	        if (split.length == 2)
	            cookie = new BasicClientCookie(split[0], split[1]);
	        else
	            cookie = new BasicClientCookie(split[0], null);

	        cookie.setDomain(domain);
	        cs.addCookie(cookie);
	    }
	    return cs;
	}
	*/
}
