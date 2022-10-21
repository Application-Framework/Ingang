package com.spring.ex.dto.course.request;

import java.sql.Time;

public class RequestCourseVideoDTO {
	private int rolv_no;
	private int roli_no;
	private String title;
	private String s_file_name;
	private Time playtime;
	private int order;
	
	public int getRolv_no() {
		return rolv_no;
	}
	public void setRolv_no(int rolv_no) {
		this.rolv_no = rolv_no;
	}
	public int getRoli_no() {
		return roli_no;
	}
	public void setRoli_no(int roli_no) {
		this.roli_no = roli_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getS_file_name() {
		return s_file_name;
	}
	public void setS_file_name(String s_file_name) {
		this.s_file_name = s_file_name;
	}
	public Time getPlaytime() {
		return playtime;
	}
	public void setPlaytime(Time playtime) {
		this.playtime = playtime;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "RequestCourseVideoDTO [rolv_no=" + rolv_no + ", roli_no=" + roli_no + ", title=" + title
				+ ", s_file_name=" + s_file_name + ", playtime=" + playtime + ", order=" + order + "]";
	}
}
