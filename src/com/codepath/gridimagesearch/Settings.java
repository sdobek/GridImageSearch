package com.codepath.gridimagesearch;
import java.io.Serializable;


public class Settings implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7503086199401682499L;
	private String itemSize;
	private String colorFilter;
	private String itemType;
	private String urlFilter;
	
	public Settings(String itemSize, String colorFilter, String itemType, String urlFilter) {
		this.itemSize = itemSize;
		this.colorFilter = colorFilter;
		this.itemType = itemType;
		this.urlFilter = urlFilter;
	}
	
	public void setFields(String itemSize, String colorFilter, String itemType, String urlFilter) {
		this.itemSize = itemSize;
		this.colorFilter = colorFilter;
		this.itemType = itemType;
		this.urlFilter = urlFilter;
	}
	
	public String getItemSize(){
		return itemSize;
	}
	
	public String getColorFilter(){
		return colorFilter;
	}
	
	public String getItemType(){
		return itemType;
	}
	
	public String getUrlFilter(){
		return urlFilter;
	}
}
