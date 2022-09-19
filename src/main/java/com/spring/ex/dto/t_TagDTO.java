package com.spring.ex.dto;

public class t_TagDTO {
	private int tag_no;
	private String tag_name;
	private String tag_abbr;
	
	public int getTag_no() {
		return tag_no;
	}
	public void setTag_no(int tag_no) {
		this.tag_no = tag_no;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public String getTag_abbr() {
		return tag_abbr;
	}
	public void setTag_abbr(String tag_abbr) {
		this.tag_abbr = tag_abbr;
	}
	@Override
	public String toString() {
		return "t_TagDTO [tag_no=" + tag_no + ", tag_name=" + tag_name + ", tag_abbr=" + tag_abbr + "]";
	}
}
