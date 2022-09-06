package com.spring.ex.dto.course;

public class CourseFileUploadDTO {
	private int olfu_no;
	private int oli_no;
	private String url;
	
	public int getOlfu_no() {
		return olfu_no;
	}
	public void setOlfu_no(int olfu_no) {
		this.olfu_no = olfu_no;
	}
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "CourseFileUploadDTO [olfu_no=" + olfu_no + ", oli_no=" + oli_no + ", url=" + url + "]";
	}
}
