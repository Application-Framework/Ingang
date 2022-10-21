package com.spring.ex.dto.course;

public class CourseSubTypeDTO {
	private int oli_no;
	private int sub_type_no;
	
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
	}
	public int getSub_type_no() {
		return sub_type_no;
	}
	public void setSub_type_no(int sub_type_no) {
		this.sub_type_no = sub_type_no;
	}
	@Override
	public String toString() {
		return "CourseSubTypeDTO [oli_no=" + oli_no + ", sub_type_no=" + sub_type_no + "]";
	}
}
