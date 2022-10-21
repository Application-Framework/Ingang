package com.spring.ex.dto.course.request;

public class RequestCourseTagDTO {
	private int roli_no;
	private int tag_no;
	
	public int getRoli_no() {
		return roli_no;
	}
	public void setRoli_no(int roli_no) {
		this.roli_no = roli_no;
	}
	public int getTag_no() {
		return tag_no;
	}
	public void setTag_no(int tag_no) {
		this.tag_no = tag_no;
	}
	@Override
	public String toString() {
		return "RequestCourseTagDTO [roli_no=" + roli_no + ", tag_no=" + tag_no + "]";
	}
}
