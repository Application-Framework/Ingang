package com.spring.ex.dto.note;

import java.sql.Date;

public class NoteReplyDTO {
	private int nr_no;
	private int n_no;
	private int m_no;
	private int star_rating;
	private String content;
	private Date reg_date;
	
	public int getNr_no() {
		return nr_no;
	}
	public void setNr_no(int nr_no) {
		this.nr_no = nr_no;
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
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
		return "NoteReplyDTO [nr_no=" + nr_no + ", n_no=" + n_no + ", m_no=" + m_no + ", star_rating=" + star_rating
				+ ", content=" + content + ", reg_date=" + reg_date + "]";
	}
}
