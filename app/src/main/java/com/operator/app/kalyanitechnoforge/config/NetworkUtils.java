//package com.operator.app.kalyanitechnoforge.config;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.util.Log;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.List;
//
//
//public class NetworkUtils implements NetworkManager {
//
//	public static int TYPE_WIFI = 1;
//    public static int TYPE_MY_MOBILE = 2;
//    public static int TYPE_NOT_CONNECTED = 0;
//	public NetworkUtils()
//	{
//		// constructer
//	}
//	private static final String TAG = "NetworkManager";
//
//	@Override
//	public int isConnected(Context context)
//	{
//		//Check Internet Connection Status
//		ConnectivityManager cm = (ConnectivityManager) context
//				.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//		if (null != activeNetwork)
//		{
//			if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
//				{
//					Log.i(TAG, "Wifi Connected");
//					return TYPE_WIFI;
//				}
//			if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
//			{
//				Log.i(TAG, "Internet Connected");
//				return TYPE_MY_MOBILE;
//			}
//		}
//		Log.i(TAG, "No Connection");
//		return TYPE_NOT_CONNECTED;
//	}
//	@Override
//	public String getResponce(String strUrl, JSONObject j)
//	{
//		// TODO Get httprequest with entity(parameter) and send responce
//		System.out.println("In background");
//        try
//        {
//        	HttpClient httpclient = new DefaultHttpClient();
//            HttpPost request = new HttpPost(strUrl);
//        	StringEntity s = new StringEntity(j.toString(), "UTF-8");
//
//            s.setContentType("application/json;charset=UTF-8");
//            request.setHeader("Accept", "application/json");
//            request.setHeader("Content-Type", "application/json; charset=utf-8");
//            request.setEntity(s);
//
//            System.out.println("before");
//            HttpResponse response = httpclient.execute(request);
//            System.out.println("after");
//            int i = response.getStatusLine().getStatusCode();
//            HttpEntity entity = response.getEntity();
//            InputStream is = entity.getContent();
//            String the_string_response = convertStreamToString(is);
//            System.out.println("##responce:"+the_string_response);
//    		return the_string_response;
//        }
//        catch(Exception e)
//        {
//        	Log.e(TAG, "Exception:" + e);
//        	return "Error";
//        }
//	}
//
//	private static String convertStreamToString(InputStream is) {
//
//	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//	    StringBuilder sb = new StringBuilder();
//
//	    String line = null;
//	    try {
//	        while ((line = reader.readLine()) != null) {
//	            sb.append((line + "\n"));
//	        }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    } finally {
//	        try {
//	            is.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    return sb.toString();
//	}
//
//	public String getResponce(String strUrl)
//	{
//		// TODO Get httprequest with entity(parameter) and send responce
//		System.out.println("In background");
//        try
//        {
//        	HttpClient httpclient = new DefaultHttpClient();
//            HttpPost request = new HttpPost(strUrl);
//        	//StringEntity s = new StringEntity(j.toString(), "UTF-8");
//
//           // s.setContentType("application/json;charset=UTF-8");
//            request.setHeader("Accept", "application/json");
//           request.setHeader("Content-Type", "application/json; charset=utf-8");
//            //request.setEntity(s);
//
//            System.out.println("before");
//            HttpResponse response = httpclient.execute(request);
//            System.out.println("after");
//            int i = response.getStatusLine().getStatusCode();
//            HttpEntity entity = response.getEntity();
//            InputStream is = entity.getContent();
//            String the_string_response = convertStreamToString(is);
//            System.out.println("##responce:"+the_string_response);
//    		return the_string_response;
//        }
//        catch(Exception e)
//        {
//        	Log.e(TAG, "Exception:" + e);
//        	return "Error";
//        }
//	}
//
//	public String getNormalResponce(String strUrl, List<NameValuePair> list)
//	{
//		// TODO Get httprequest with entity(parameter) and send responce
//		System.out.println("In background");
//        try
//        {
//        	HttpClient httpclient = new DefaultHttpClient();
//            HttpPost request = new HttpPost(strUrl);
//        	request.setEntity(new UrlEncodedFormEntity(list));
//
//        	request.setHeader("Accept", "application/json");
//            request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
//            System.out.println("before");
//            HttpResponse response = httpclient.execute(request);
//            System.out.println("after");
//            int i = response.getStatusLine().getStatusCode();
//            HttpEntity entity = response.getEntity();
//            InputStream is = entity.getContent();
//            String the_string_response = convertStreamToString(is);
//            System.out.println("##responce:"+the_string_response);
//    		return the_string_response;
//        }
//        catch(Exception e)
//        {
//        	Log.e(TAG, "Exception:" + e);
//        	return "Error";
//        }
//	}
//}
