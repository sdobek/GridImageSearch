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
		if (itemSize.equals("-----")){
			return "";
		}
		return itemSize;
	}
	
	public String getColorFilter(){
		if (colorFilter.equals("-----")){
			return "";
		}
		return colorFilter;
	}
	
	public String getItemType(){
		if (itemType.equals("-----")){
			return "";
		}
		return itemType;
	}
	
	public String getUrlFilter(){
		return urlFilter;
	}
}
