package com.spring.ex.dto;

import java.sql.Time;

public class CourseVideoDTO {
	private int olv_no;
	private int oli_no;
	private String s_file_name;
	private Time playtime;
	
	public int getOlv_no() {
		return olv_no;
	}
	public void setOlv_no(int olv_no) {
		this.olv_no = olv_no;
	}
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
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
	@Override
	public String toString() {
		return "CourseVideoDTO [olv_no=" + olv_no + ", oli_no=" + oli_no + ", s_file_name=" + s_file_name
				+ ", playtime=" + playtime + "]";
	}
}
