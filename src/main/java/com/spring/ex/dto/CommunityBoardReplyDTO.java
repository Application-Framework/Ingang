package com.spring.ex.dto;

public class CommunityBoardReplyDTO {
	private int cbr_no;
	private int cb_no;
	private int m_no;
	private String content;
	private Object reg_date;
	public int getCbr_no() {
		return cbr_no;
	}
	public void setCbr_no(int cbr_no) {
		this.cbr_no = cbr_no;
	}
	public int getCb_no() {
		return cb_no;
	}
	public void setCb_no(int cb_no) {
		this.cb_no = cb_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Object getReg_date() {
		return reg_date;
	}
	public void setReg_date(Object reg_date) {
		this.reg_date = reg_date;
	}
	
	
}