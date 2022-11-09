package com.spring.ex.dto;

public class InquiryDTO {
	private int inq_no;
	private int m_no;
	private String category;
	private String title;
	private String content;
	private int statement;
	private Object reg_date;
	public int getInq_no() {
		return inq_no;
	}
	public void setInq_no(int inq_no) {
		this.inq_no = inq_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public int getStatement() {
		return statement;
	}
	public void setStatement(int statement) {
		this.statement = statement;
	}
	public Object getReg_date() {
		return reg_date;
	}
	public void setReg_date(Object reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
}