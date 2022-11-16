package com.spring.ex.dto;

public class TeacherRequestDTO {
	private int oltr_no;
	private int m_no;
	private String email;
	private String name;
	private String phone;
	private int main_type_no;
	private String introduction;
	private String link;
	private String rejection_message;
	private int approval_status;
	private Object request_date;
	
	public int getOltr_no() {
		return oltr_no;
	}
	public void setOltr_no(int oltr_no) {
		this.oltr_no = oltr_no;
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
	public String getRejection_message() {
		return rejection_message;
	}
	public void setRejection_message(String rejection_message) {
		this.rejection_message = rejection_message;
	}
	public int getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(int approval_status) {
		this.approval_status = approval_status;
	}
	public Object getRequest_date() {
		return request_date;
	}
	public void setRequest_date(Object request_date) {
		this.request_date = request_date;
	}
	@Override
	public String toString() {
		return "TeacherRequestDTO [oltr_no=" + oltr_no + ", m_no=" + m_no + ", email=" + email + ", name=" + name
				+ ", phone=" + phone + ", main_type_no=" + main_type_no + ", introduction=" + introduction + ", link="
				+ link + ", rejection_message=" + rejection_message + ", approval_status=" + approval_status
				+ ", request_date=" + request_date + "]";
	}
}
