package com.spring.ex.dto;

public class CommunityTagSerachDTO {
	private int cts_no;
	private int ctl_no;
	private Object cts_date;
	private int classify;
	private int cts_found;
	
	public int getClassify() {
		return classify;
	}
	public void setClassify(int classify) {
		this.classify = classify;
	}
	public int getCts_no() {
		return cts_no;
	}
	public void setCts_no(int cts_no) {
		this.cts_no = cts_no;
	}
	public int getCtl_no() {
		return ctl_no;
	}
	public void setCtl_no(int ctl_no) {
		this.ctl_no = ctl_no;
	}
	public Object getCts_date() {
		return cts_date;
	}
	public void setCts_date(Object cts_date) {
		this.cts_date = cts_date;
	}
	public int getCts_found() {
		return cts_found;
	}
	public void setCts_found(int cts_found) {
		this.cts_found = cts_found;
	}
	
	
}