package com.fh.entity;

import java.io.Serializable;

public class SearchParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2114175528674615061L;
	private String keyword;
	private String begintime;
	private String endtime;
	private String type;
	private String status;

	
	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getBegintime() {
		return begintime;
	}


	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}


	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "SearchParameter [keyword=" + keyword + ", begintime=" + begintime + ", endtime=" + endtime +"]";
	}

}
