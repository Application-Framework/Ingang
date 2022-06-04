package com.spring.ex.dto.note;

public class NoteLikeDTO {
	private int nl_no;
	private int n_no;
	private int m_no;
	
	public int getNl_no() {
		return nl_no;
	}
	public void setNl_no(int nl_no) {
		this.nl_no = nl_no;
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	@Override
	public String toString() {
		return "NoteLikeDTO [nl_no=" + nl_no + ", n_no=" + n_no + ", m_no=" + m_no + "]";
	}
}
