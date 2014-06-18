package com.fcduarte.gimagesearcher.model;

import java.io.Serializable;

public class ImageSearchPreferences implements Serializable {
	
	private static final long serialVersionUID = 2782819431886467919L;
	public static final String SITE_FILTER_KEY = "as_sitesearch";
	public static final String IMAGE_TYPE_KEY = "imgtype";
	public static final String COLOR_FILTER_KEY = "imgcolor";
	public static final String IMAGE_SIZE_KEY = "imgsz";
	
	private String imageSize;
	private String colorFilter;
	private String imageType;
	private String siteFilter;
	
	public ImageSearchPreferences(String imageSize, String colorFilter, String imageType,
			String siteFilter) {
		super();
		this.imageSize = imageSize;
		this.colorFilter = colorFilter;
		this.imageType = imageType;
		this.siteFilter = siteFilter;
	}
	
	public String getImageSize() {
		return imageSize;
	}
	
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	
	public String getColorFilter() {
		return colorFilter;
	}
	
	public void setColorFilter(String colorFilter) {
		this.colorFilter = colorFilter;
	}
	
	public String getImageType() {
		return imageType;
	}
	
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String getSiteFilter() {
		return siteFilter;
	}
	
	public void setSiteFilter(String siteFilter) {
		this.siteFilter = siteFilter;
	}
	
}
