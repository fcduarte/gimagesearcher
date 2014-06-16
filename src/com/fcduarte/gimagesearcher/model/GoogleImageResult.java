package com.fcduarte.gimagesearcher.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleImageResult {

	private ArrayList<GoogleImage> googleImagesList;

	public GoogleImageResult(JSONObject response) {
		this.googleImagesList = new ArrayList<>();
		
		try {
			JSONObject responseData = (JSONObject) response.get("responseData");
			JSONArray results = responseData.getJSONArray("results");
			
			if (results.length() > 0) {
				for (int i = 0; i < results.length(); i++) {
					JSONObject result = (JSONObject) results.get(i);
					String url = result.getString("url"); 
					String thumbnailUrl = result.getString("tbUrl");
					googleImagesList.add(new GoogleImage(thumbnailUrl, url));
				}

			}
		} catch (JSONException e) {
		}
	}
	
	public ArrayList<GoogleImage> getGoogleImagesList() {
		return googleImagesList;
	}

	public void setGoogleImagesList(ArrayList<GoogleImage> googleImagesList) {
		this.googleImagesList = googleImagesList;
	}
	
}
