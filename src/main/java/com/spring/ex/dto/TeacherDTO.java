package com.spring.ex.dto;

import java.sql.Date;

public class TeacherDTO {
	private int olt_no;
	private int m_no;
	private String email;
	private String name;
	private String phone;
	private int main_type_no;
	private String introduction;
	private String link;
	private int grade;
	private Date reg_date;
	
	public int getOlt_no() {
		return olt_no;
	}
	public void setOlt_no(int olt_no) {
		this.olt_no = olt_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getMain_type_no() {
		return main_type_no;
	}
	public void setMain_type_no(int main_type_no) {
		this.main_type_no = main_type_no;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "TeacherDTO [olt_no=" + olt_no + ", m_no=" + m_no + ", email=" + email + ", name=" + name + ", phone="
				+ phone + ", main_type_no=" + main_type_no + ", introduction=" + introduction + ", link=" + link
				+ ", grade=" + grade + ", reg_date=" + reg_date + "]";
	}
}
