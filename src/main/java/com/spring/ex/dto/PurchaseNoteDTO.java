package com.spring.ex.dto;

import java.util.Date;

public class PurchaseNoteDTO {
	
	private int hon_no;
	private int n_no;
	private int m_no;
	private int payment;
	private Date payment_date;
	private int payment_status;
	
	public int getHon_no() {
		return hon_no;
	}
	public void setHon_no(int hon_no) {
		this.hon_no = hon_no;
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
}
