package com.spring.ex.dto;

import java.sql.Date;

public class CourseDTO {
	private int oli_no;
	private int olt_no;
	private String title;
	private String content;
	private int hit;
	private String img_path;
	private int price;
	private Date reg_date;
	private int like;
	private int enable;
	
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
	}
	public int getOlt_no() {
		return olt_no;
	}
	public void setOlt_no(int olt_no) {
		this.olt_no = olt_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
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
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	
	@Override
	public String toString() {
		return "CourseDTO [oli_no=" + oli_no + ", olt_no=" + olt_no + ", title=" + title + ", content=" + content
				+ ", hit=" + hit + ", img_path=" + img_path + ", price=" + price + ", reg_date=" + reg_date
				+ ", enable=" + enable + "]";
	}
}
