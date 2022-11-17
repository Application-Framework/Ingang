package com.spring.ex.dto;

import java.sql.Date;

public class HistoryOrderLectureDTO {
	private int hol_no;
	private int oli_no;
	private int m_no;
	private int payment;
	private Date payment_date;
	private int payment_status;
	
	private int total;
	private Date selected_date;
	
	public int getHol_no() {
		return hol_no;
	}
	public void setHol_no(int hol_no) {
		this.hol_no = hol_no;
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
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public int getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}
	@Override
	public String toString() {
		return "HistoryOrderLectureDTO [hol_no=" + hol_no + ", oli_no=" + oli_no + ", m_no=" + m_no + ", payment="
				+ payment + ", payment_date=" + payment_date + ", payment_status=" + payment_status + "]";
	}
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Date getSelected_date() {
		return selected_date;
	}
	public void setSelected_date(Date selected_date) {
		this.selected_date = selected_date;
	}
}
