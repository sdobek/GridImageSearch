package com.codepath.gridimagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	private final int REQUEST_CODE = 20;
	public static final String ITEM_SIZE = "itemSize";
	public static final String COLOR_FILTER = "colorFilter";
	public static final String ITEM_TYPE = "itemType";
	public static final String URL_FILTER = "urlFilter";
	
	EditText etQuery;
	GridView gvImageDisplay;
	Button btnSearch;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	Settings itemSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvImageDisplay.setAdapter(imageAdapter);
		gvImageDisplay.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long rowId){
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult ir = imageResults.get(position);
				i.putExtra("result", ir);
				startActivity(i);
			}
		});
		gvImageDisplay.setOnScrollListener(new EndlessScrollListener() {
	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	        	findAdditionalImages(totalItemsCount);
	        }
	    });
		itemSettings = null;
	}

	private void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvImageDisplay= (GridView) findViewById(R.id.gvImageDisplay);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		
	}
	
	public void onImageSearch (View v){
		imageResults.clear();
		imageAdapter.clear();
		findAdditionalImages(0);
	}
	
	public void findAdditionalImages (int page){
		String query = etQuery.getText().toString();
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" +
                "start=" + page + "&v=1.0"+getSettingFields()+"&q=" + Uri.encode(query), 
                new JsonHttpResponseHandler(){
					public void onSuccess(JSONObject response){
						JSONArray imageJsonResults = null;
						try {
							imageJsonResults = response.getJSONObject(
									"responseData").getJSONArray("results");
							imageAdapter.addAll(ImageResult
									.fromJSONArray(imageJsonResults));
							//imageResults.clear();
							Log.d("DEBUG", imageResults.toString());
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				} );
	}
	
	public void onShowSettings(MenuItem mi){
		Intent i = new Intent(this, SettingsActivity.class);
		i.putExtra("settings", itemSettings);
		startActivityForResult(i, REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
	     Settings s = (Settings) data.getSerializableExtra("settings");
	     itemSettings = s;
	     onImageSearch(findViewById(R.id.btnSearch));  
	  }
	}
	
	public String getSettingFields(){
		if (itemSettings != null){
			return "&imgsz="+itemSettings.getItemSize()+"&imgcolor="+itemSettings.getColorFilter()
						+"&imgtype="+itemSettings.getItemType()+"&as_sitesearch="+itemSettings.getUrlFilter();
		}
		else {
			return "";
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
