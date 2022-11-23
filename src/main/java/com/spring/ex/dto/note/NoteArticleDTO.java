package com.spring.ex.dto.note;

public class NoteArticleDTO {
	private int na_no;
	private int n_no;
	private int order;
	private String title;
	private String content;
	
	public int getNa_no() {
		return na_no;
	}
	public void setNa_no(int na_no) {
		this.na_no = na_no;
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "NoteArticleDTO [na_no=" + na_no + ", n_no=" + n_no + ", order=" + order + ", title=" + title
				+ ", content=" + content + "]";
	}
}
