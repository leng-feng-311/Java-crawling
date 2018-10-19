package com.jason.springboot.utils;

import com.jason.springboot.application.DTO.PunishInfo;
import com.jason.springboot.application.DTO.SearchItem;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{

	private long totalPages;
	private long recordCount;
	private List<PunishInfo> itemList;
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public List<PunishInfo> getItemList() {
		return itemList;
	}
	public void setItemList(List<PunishInfo> itemList) {
		this.itemList = itemList;
	}
	
	
	
}
