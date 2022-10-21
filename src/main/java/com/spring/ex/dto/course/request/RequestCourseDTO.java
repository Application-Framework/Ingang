package com.spring.ex.dto.course.request;

import java.sql.Date;

public class RequestCourseDTO {
	private int olr_no;
	private int olt_no;
	private int oli_no;
	private String title;
	private String introduction;
	private String content;
	private String img_path;
	private int price;
	private int level;
	private Date req_date;
	private int approval;
	private String rejection_message;
	
	public int getOlr_no() {
		return olr_no;
	}
	public void setOlr_no(int olr_no) {
		this.olr_no = olr_no;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Date getReq_date() {
		return req_date;
	}
	public void setReq_date(Date req_date) {
		this.req_date = req_date;
	}
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
	public String getRejection_message() {
		return rejection_message;
	}
	public void setRejection_message(String rejection_message) {
		this.rejection_message = rejection_message;
	}
	@Override
	public String toString() {
		return "CourseRequestDTO [olr_no=" + olr_no + ", olt_no=" + olt_no + ", oli_no=" + oli_no + ", title=" + title
				+ ", introduction=" + introduction + ", content=" + content + ", img_path=" + img_path + ", price="
				+ price + ", level=" + level + ", req_date=" + req_date + ", approval=" + approval
				+ ", rejection_message=" + rejection_message + "]";
	}
}
