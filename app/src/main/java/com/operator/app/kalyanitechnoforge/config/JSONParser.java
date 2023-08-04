//package com.operator.app.kalyanitechnoforge.config;
//
//import android.util.Log;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class JSONParser {
//
//    static InputStream is = null;
//    static JSONObject jobj = null;
//    static String json = "";
//
//    public JSONParser() {
//
//    }
//    public JSONObject getJSONFromUrl(String url) {
//
//        // Making HTTP request
//
//        // defaultHttpClient
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpUriRequest request = new HttpGet(url);
//        HttpResponse httpResponse = null;
//        try {
//            httpResponse = httpClient.execute(request);
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        HttpEntity httpEntity = httpResponse.getEntity();
//        try {
//            json = EntityUtils.toString(httpEntity);
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            jobj = new JSONObject(json);
//        } catch (JSONException e) {
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//        }
//
//        // return JSON String
//        return jobj;
//
//    }
//
//
//}
