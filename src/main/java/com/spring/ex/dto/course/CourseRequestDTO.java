package com.spring.ex.dto.course;

import java.sql.Date;

public class CourseRequestDTO {
	private int olr_no;
	private int oli_no;
	private int origin_oli_no;
	private int approval_status;
	private String rejection_message;
	private Date request_datetime;
	
	public int getOlr_no() {
		return olr_no;
	}
	public void setOlr_no(int olr_no) {
		this.olr_no = olr_no;
	}
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
	}
	public int getOrigin_oli_no() {
		return origin_oli_no;
	}
	public void setOrigin_oli_no(int origin_oli_no) {
		this.origin_oli_no = origin_oli_no;
	}
	public int getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(int approval_status) {
		this.approval_status = approval_status;
	}
	public String getRejection_message() {
		return rejection_message;
	}
	public void setRejection_message(String rejection_message) {
		this.rejection_message = rejection_message;
	}
	public Date getRequest_datetime() {
		return request_datetime;
	}
	public void setRequest_datetime(Date request_datetime) {
		this.request_datetime = request_datetime;
	}
	@Override
	public String toString() {
		return "CourseRequestDTO [olr_no=" + olr_no + ", oli_no=" + oli_no + ", origin_oli_no=" + origin_oli_no
				+ ", approval_status=" + approval_status + ", rejection_message=" + rejection_message
				+ ", request_datetime=" + request_datetime + "]";
	}
}
