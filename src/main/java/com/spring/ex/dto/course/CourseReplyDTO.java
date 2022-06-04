package com.spring.ex.dto;

import java.sql.Date;

public class CourseReplyDTO {
	private int olr_no;
	private int oli_no;
	private int m_no;
	private int star_rating;
	private String content;
	private Date reg_date;
	
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
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public int getStar_rating() {
		return star_rating;
	}
	public void setStar_rating(int star_rating) {
		this.star_rating = star_rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "CourseReplyDTO [olr_no=" + olr_no + ", oli_no=" + oli_no + ", m_no=" + m_no + ", star_rating="
				+ star_rating + ", content=" + content + ", reg_date=" + reg_date + "]";
	}
}
