package com.codepath.gridimagesearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable{
	private static final long serialVersionUID = 2636389073439281291L;
	private String fullUrl;
	private String thumbUrl;
	
	public ImageResult (JSONObject json){
		try {
			this.fullUrl = json.getString("url");
			this.thumbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}
	}
	
	public String getFullUrl(){
		return fullUrl;
	}
	
	public String getThumbUrl(){
		return thumbUrl;
	}
	
	public String toString() {
		return this.thumbUrl;
	}

	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray imageJsonResults) {
		ArrayList<ImageResult> results =  new ArrayList<ImageResult>();
		for (int index = 0; index < imageJsonResults.length(); index++){
			try {
				results.add(new ImageResult(imageJsonResults.getJSONObject(index)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}
}
