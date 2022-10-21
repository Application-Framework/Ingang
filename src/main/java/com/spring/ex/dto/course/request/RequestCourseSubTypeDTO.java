package com.spring.ex.dto.course.request;

public class RequestCourseSubTypeDTO {
	private int roli_no;
	private int sub_type_no;
	private int order;
	
	public int getRoli_no() {
		return roli_no;
	}
	public void setRoli_no(int roli_no) {
		this.roli_no = roli_no;
	}
	public int getSub_type_no() {
		return sub_type_no;
	}
	public void setSub_type_no(int sub_type_no) {
		this.sub_type_no = sub_type_no;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "RequestCourseSubTypeDTO [roli_no=" + roli_no + ", sub_type_no=" + sub_type_no + ", order=" + order
				+ "]";
	}
}
