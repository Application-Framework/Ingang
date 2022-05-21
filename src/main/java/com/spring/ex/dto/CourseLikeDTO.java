package com.spring.ex.dto;

public class CourseLikeDTO {
	private int oll_no;
	private int oli_no;
	private int m_no;
	
	public int getOll_no() {
		return oll_no;
	}
	public void setOll_no(int oll_no) {
		this.oll_no = oll_no;
	}
	public int getOli_no() {
		return oli_no;
	}
	public void setOli_no(int oli_no) {
		this.oli_no = oli_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	@Override
	public String toString() {
		return "CourseLikeDTO [oll_no=" + oll_no + ", oli_no=" + oli_no + ", m_no=" + m_no + "]";
	}
}
