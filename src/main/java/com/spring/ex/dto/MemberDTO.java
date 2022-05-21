package com.spring.ex.dto;

import java.sql.Date;

public class MemberDTO {
	private int m_no;
	private String m_id;
	private String m_pw;
	private String m_name;
	private Date m_birth;
	private int m_sex;
	private String m_phone;
	private int m_authority;
	private Date reg_date;
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public Date getM_birth() {
		return m_birth;
	}
	public void setM_birth(Date m_birth) {
		this.m_birth = m_birth;
	}
	public int getM_sex() {
		return m_sex;
	}
	public void setM_sex(int m_sex) {
		this.m_sex = m_sex;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public int getM_authority() {
		return m_authority;
	}
	public void setM_authority(int m_authority) {
		this.m_authority = m_authority;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
