package com.spring.ex.dto;

public class CommunityBoardDTO {
	private int cb_no;
	private int m_no;
	private String title;
	private String content;
	private int hit;
	private String img_path;
	private String reg_date;
	private String classify;
	private int enable;
	
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
	public String getReg_date() {
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
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "CommunityBoardDTO [cb_no=" + cb_no + ", m_no=" + m_no + ", title=" + title + ", content=" + content
				+ ", hit=" + hit + ", img_path=" + img_path + ", reg_date=" + reg_date + ", classify=" + classify
				+ ", enable=" + enable + "]";
	}
	
	
	
}