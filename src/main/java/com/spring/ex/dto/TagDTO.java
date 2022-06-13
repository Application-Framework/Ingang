package com.spring.ex.dto;

import java.util.Date;

public class TagDTO {
	private int t_no;
	private String t_name;
	private int t_viewCnt;
	private Date t_regDate;
	
	public int getT_no() {
		return t_no;
	}
	public void setT_no(int t_no) {
		this.t_no = t_no;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public int getT_viewCnt() {
		return t_viewCnt;
	}
	public void setT_viewCnt(int t_viewCnt) {
		this.t_viewCnt = t_viewCnt;
	}
	public Date getT_regDate() {
		return t_regDate;
	}
	public void setT_regDate(Date t_regDate) {
		this.t_regDate = t_regDate;
	}
}
