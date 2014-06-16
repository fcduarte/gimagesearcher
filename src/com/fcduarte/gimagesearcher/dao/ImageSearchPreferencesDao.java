package com.fcduarte.gimagesearcher.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fcduarte.gimagesearcher.model.ImageSearchPreferences;

public class ImageSearchPreferencesDao {

	private SharedPreferences sharedPreferences;

	public ImageSearchPreferencesDao(Context context) {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public ImageSearchPreferences get() {
		String siteFilter = sharedPreferences.getString(
				ImageSearchPreferences.SITE_FILTER_KEY, "");
		String imageType = sharedPreferences.getString(
				ImageSearchPreferences.IMAGE_TYPE_KEY, "");
		String colorFilter = sharedPreferences.getString(
				ImageSearchPreferences.COLOR_FILTER_KEY, "");
		String imageSize = sharedPreferences.getString(
				ImageSearchPreferences.IMAGE_SIZE_KEY, "large");

		return new ImageSearchPreferences(imageSize, colorFilter, imageType,
				siteFilter);
	}

}
