package com.fcduarte.gimagesearcher.model;

public class GoogleImage {

	private String thumbnailUrl;
	private String url;

	public GoogleImage(String thumbnailUrl, String url) {
		super();
		this.thumbnailUrl = thumbnailUrl;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
}
