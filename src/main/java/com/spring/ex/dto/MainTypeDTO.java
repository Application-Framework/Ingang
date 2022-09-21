package com.spring.ex.dto;

public class MainTypeDTO {
	private int main_type_no;
	private String main_type_name;
	private String main_type_abbr;
	private int main_type_order;
	
	public int getMain_type_no() {
		return main_type_no;
	}
	public void setMain_type_no(int main_type_no) {
		this.main_type_no = main_type_no;
	}
	public String getMain_type_name() {
		return main_type_name;
	}
	public void setMain_type_name(String main_type_name) {
		this.main_type_name = main_type_name;
	}
	public String getMain_type_abbr() {
		return main_type_abbr;
	}
	public void setMain_type_abbr(String main_type_abbr) {
		this.main_type_abbr = main_type_abbr;
	}
	public int getMain_type_order() {
		return main_type_order;
	}
	public void setMain_type_order(int main_type_order) {
		this.main_type_order = main_type_order;
	}
	@Override
	public String toString() {
		return "MainTypeDTO [main_type_no=" + main_type_no + ", main_type_name=" + main_type_name + ", main_type_abbr="
				+ main_type_abbr + ", main_type_order=" + main_type_order + "]";
	}
}
