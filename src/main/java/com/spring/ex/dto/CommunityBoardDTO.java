package com.spring.ex.dto;

public class CommunityBoardDTO {
	private int cb_no;
	private int m_no;
	private int oli_no;
	private String title;
	private String content;
	private int hit;
	private String img_path;
	private Object reg_date;
	private String classify;
	private int flag;
	
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
	public Object getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public int getflag() {
		return flag;
	}
	public void setflag(int flag) {
		this.flag = flag;
	}
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
	}
	
	
	
}