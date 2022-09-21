package com.spring.ex.dto;

public class SubTypeDTO {
	private int sub_type_no;
	private int main_type_no;
	private String sub_type_name;
	private String sub_type_abbr;
	private int sub_type_order;
	
	public int getSub_type_no() {
		return sub_type_no;
	}
	public void setSub_type_no(int sub_type_no) {
		this.sub_type_no = sub_type_no;
	}
	public int getMain_type_no() {
		return main_type_no;
	}
	public void setMain_type_no(int main_type_no) {
		this.main_type_no = main_type_no;
	}
	public String getSub_type_name() {
		return sub_type_name;
	}
	public void setSub_type_name(String sub_type_name) {
		this.sub_type_name = sub_type_name;
	}
	public String getSub_type_abbr() {
		return sub_type_abbr;
	}
	public void setSub_type_abbr(String sub_type_abbr) {
		this.sub_type_abbr = sub_type_abbr;
	}
	public int getSub_type_order() {
		return sub_type_order;
	}
	public void setSub_type_order(int sub_type_order) {
		this.sub_type_order = sub_type_order;
	}
	@Override
	public String toString() {
		return "SubTypeDTO [sub_type_no=" + sub_type_no + ", main_type_no=" + main_type_no + ", sub_type_name="
				+ sub_type_name + ", sub_type_abbr=" + sub_type_abbr + ", sub_type_order=" + sub_type_order + "]";
	}
}
