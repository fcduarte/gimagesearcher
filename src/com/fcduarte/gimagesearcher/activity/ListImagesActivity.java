package com.fcduarte.gimagesearcher.activity;

import java.util.ArrayList;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SearchView;

import com.fcduarte.gimagesearcher.R;
import com.fcduarte.gimagesearcher.adapter.ImageGridViewAdapter;
import com.fcduarte.gimagesearcher.listener.EndlessScrollListener;
import com.fcduarte.gimagesearcher.model.GoogleImage;
import com.fcduarte.gimagesearcher.model.GoogleImageResult;
import com.fcduarte.gimagesearcher.rest.GoogleImageSearchRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ListImagesActivity extends Activity {

	private static final int TOTAL_IMAGES_PER_PAGE = 8;

	public static final int REQUEST_EDIT_PREFERENCES = 10;
	
	private GridView mImagesGridView;
	private ImageGridViewAdapter mImageGridViewAdapter;
	private GoogleImageSearchRestClient mRestClient;
	private String mQuery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleIntent(getIntent());
		
		setContentView(R.layout.activity_list_images);
		
		mRestClient = new GoogleImageSearchRestClient(this);
		
		mImagesGridView = (GridView) findViewById(R.id.images_grid_view);
		mImagesGridView.setEmptyView(findViewById(R.id.empty_text_view));
		
		mImageGridViewAdapter = new ImageGridViewAdapter(this);
		mImagesGridView.setAdapter(mImageGridViewAdapter);
		
		mImagesGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String url = mImageGridViewAdapter.getItem(position).getUrl();
				
				Intent intent = new Intent(view.getContext(), ImageFullscreenActivity.class);
				intent.putExtra(ImageFullscreenActivity.IMAGE_URL_KEY, url);
				startActivity(intent);
			}
		});
		
		mImagesGridView.setOnScrollListener(new EndlessScrollListener() {
			
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
	            mRestClient.get(mQuery, page * TOTAL_IMAGES_PER_PAGE, imagesResponseHandler);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		
	    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView = (SearchView) menu.findItem(R.id.search_menu_item).getActionView();
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		return true;
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(getIntent());
	}
	
	public void onPreferencesMenuItemClicked(MenuItem menuItem) {
		Intent intent = new Intent(this, ImageSearchPreferenceActivity.class);
		startActivityForResult(intent, REQUEST_EDIT_PREFERENCES);
	}

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mQuery = intent.getStringExtra(SearchManager.QUERY);
            
            mImageGridViewAdapter.setImages(new ArrayList<GoogleImage>());
            mRestClient.get(mQuery, imagesResponseHandler);
        }
    }
    
    private JsonHttpResponseHandler imagesResponseHandler = new JsonHttpResponseHandler() {
		@Override
		public void onSuccess(JSONObject response) {
			super.onSuccess(response);
			
			int responseStatus = 0;
			try {
				responseStatus = response.getInt("responseStatus");
			} catch (JSONException e) {
			}
			
			if (responseStatus == HttpStatus.SC_OK) {
				GoogleImageResult result = new GoogleImageResult(response);
				
				if (!result.getGoogleImagesList().isEmpty()) {
					mImageGridViewAdapter.addImages(result.getGoogleImagesList());
					mImageGridViewAdapter.notifyDataSetChanged();
				}
			}
		}
    }; 

}
