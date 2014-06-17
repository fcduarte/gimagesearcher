package com.fcduarte.gimagesearcher.adapter;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fcduarte.gimagesearcher.R;
import com.fcduarte.gimagesearcher.model.GoogleImage;
import com.fcduarte.gimagesearcher.view.SquaredImageView;
import com.squareup.picasso.Picasso;

public class ImageGridViewAdapter extends BaseAdapter {

	private final Context context;
	private List<GoogleImage> images = new ArrayList<GoogleImage>();

	public ImageGridViewAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public GoogleImage getItem(int position) {
		return images.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SquaredImageView view = (SquaredImageView) convertView;
		if (view == null) {
			view = new SquaredImageView(context);
			view.setScaleType(CENTER_CROP);
		}

		// Get the image URL for the current position.
		GoogleImage image = getItem(position);
		String url = image.getThumbnailUrl();

		// Trigger the download of the URL asynchronously into the image view.
		Picasso.with(context) //
				.load(url) //
				.placeholder(R.drawable.placeholder) //
				.fit() //
				.into(view);

		return view;
	}
	
	public void setImages(List<GoogleImage> images) {
		this.images = images;
	}
	
	public void addImages(List<GoogleImage> images) {
		this.images.addAll(images);
	}
	

}
