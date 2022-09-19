package com.spring.ex.dto.course;

public class CourseTagDTO {
	private int oli_no;
	private int tag_no;
	
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
	}
	public int getTag_no() {
		return tag_no;
	}
	public void setTag_no(int tag_no) {
		this.tag_no = tag_no;
	}
	@Override
	public String toString() {
		return "CourseTagDTO [oli_no=" + oli_no + ", tag_no=" + tag_no + "]";
	}
}
