//package com.operator.app.kalyanitechnoforge.config;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.util.Log;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//public class NetworkUtil {
//
//
//    public static int TYPE_WIFI = 1;
//    public static int TYPE_MOBILE = 2;
//    public static int TYPE_NOT_CONNECTED = 0;
//
//
//    public static int getConnectivityStatus(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        if (null != activeNetwork) {
//            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
//                return TYPE_WIFI;
//
//            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
//                return TYPE_MOBILE;
//        }
//        return TYPE_NOT_CONNECTED;
//    }
//
//    public static String getConnectivityStatusString(Context context) {
//        int conn = NetworkUtil.getConnectivityStatus(context);
//        String status = null;
//        if (conn == NetworkUtil.TYPE_WIFI) {
//            status = "Wifi enabled";
//        } else if (conn == NetworkUtil.TYPE_MOBILE) {
//            status = "Mobile data enabled";
//        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
//            status = "Not connected to Internet";
//        }
//        return status;
//    }
//
//
//
//    public static String getResponce(String strUrl, JSONObject j)
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
//        	Log.e("TAg","Exception:"+e);
//        	return "Error";
//        }
//	}
//    public static String getResponce1(String strUrl)
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
//        	Log.e("TAG","Exception:"+e);
//        	return "Error";
//        }
//	}
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
//}