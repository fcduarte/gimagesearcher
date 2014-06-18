package com.fcduarte.gimagesearcher.rest;

import android.content.Context;

import com.fcduarte.gimagesearcher.dao.ImageSearchPreferencesDao;
import com.fcduarte.gimagesearcher.model.ImageSearchPreferences;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GoogleImageSearchRestClient {

	private static final String URL = "https://ajax.googleapis.com/ajax/services/search/images";
	private AsyncHttpClient client;
	private ImageSearchPreferencesDao dao;
	
	public GoogleImageSearchRestClient(Context context) {
		this.client = new AsyncHttpClient();
		this.dao = new ImageSearchPreferencesDao(context);
	}
	
	public void get(String query, AsyncHttpResponseHandler responseHandler) {
		this.get(query, null, responseHandler);
	}
	
	public void get(String query, Integer start, AsyncHttpResponseHandler responseHandler) {
		RequestParams params = new RequestParams();
		params.put("v", "1.0");
		params.put("q", query);
		params.put("rsz", "8");
		
		ImageSearchPreferences imageSearchPreferences = dao.get();
		params.put(ImageSearchPreferences.IMAGE_SIZE_KEY, imageSearchPreferences.getImageSize());
		params.put(ImageSearchPreferences.IMAGE_TYPE_KEY, imageSearchPreferences.getImageType());
		params.put(ImageSearchPreferences.COLOR_FILTER_KEY, imageSearchPreferences.getColorFilter());
		params.put(ImageSearchPreferences.SITE_FILTER_KEY, imageSearchPreferences.getSiteFilter());
		
		if (start != null) {
			params.put("start", String.valueOf(start));
		}
		
		client.get(URL, params, responseHandler);
	}
}
