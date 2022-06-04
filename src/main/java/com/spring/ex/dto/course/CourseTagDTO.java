package com.spring.ex.dto.course;

public class CourseTagDTO {
	private int olt_no;
	private int oli_no;
	private String tag;
	
	public int getOlt_no() {
		return olt_no;
	}
	public void setOlt_no(int olt_no) {
		this.olt_no = olt_no;
	}
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "CourseTagDTO [olt_no=" + olt_no + ", oli_no=" + oli_no + ", tag=" + tag + "]";
	}
}
