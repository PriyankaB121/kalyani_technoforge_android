package com.operator.app.kalyanitechnoforge.config;

import android.content.Context;

import org.json.JSONObject;

public interface NetworkManager 
{
	public int isConnected(Context context);
	public String getResponce(String strUrl, JSONObject j);
}