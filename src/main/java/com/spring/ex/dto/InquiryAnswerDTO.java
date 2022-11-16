package com.spring.ex.dto;

public class InquiryAnswerDTO {
	private int ia_no;
	private int inq_no;
	private String ia_content;
	private Object ia_reg_date;
	public int getIa_no() {
		return ia_no;
	}
	public void setIa_no(int ia_no) {
		this.ia_no = ia_no;
	}
	public int getInq_no() {
		return inq_no;
	}
	public void setInq_no(int inq_no) {
		this.inq_no = inq_no;
	}
	public String getIa_content() {
		return ia_content;
	}
	public void setIa_content(String ia_content) {
		this.ia_content = ia_content;
	}
	public Object getIa_reg_date() {
		return ia_reg_date;
	}
	public void setIa_reg_date(Object ia_reg_date) {
		this.ia_reg_date = ia_reg_date;
	}


}