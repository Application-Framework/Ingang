package com.spring.ex.dto.note;

import java.sql.Date;

public class NoteDTO {
	private int n_no;
	private int oil_no;
	private int m_no;
	private String title;
	private String content;
	private int price;
	private Date reg_date;
	private int classify;
	private int enable;
	
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public int getOil_no() {
		return oil_no;
	}
	public void setOil_no(int oil_no) {
		this.oil_no = oil_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
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
	public int getClassify() {
		return classify;
	}
	public void setClassify(int classify) {
		this.classify = classify;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "NoteDTO [n_no=" + n_no + ", oil_no=" + oil_no + ", m_no=" + m_no + ", title=" + title + ", content="
				+ content + ", price=" + price + ", reg_date=" + reg_date + ", classify=" + classify + ", enable="
				+ enable + "]";
	}
}
