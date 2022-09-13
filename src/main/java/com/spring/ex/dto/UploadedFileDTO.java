package com.spring.ex.dto;

import java.util.Objects;

public class UploadedFileDTO {
	private int content_no;
	private int category;
	private String url;
	
	public int getContent_no() {
		return content_no;
	}
	public void setContent_no(int content_no) {
		this.content_no = content_no;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "UploadedFileDTO [content_no=" + content_no + ", category=" + category + ", url=" + url + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadedFileDTO other = (UploadedFileDTO) obj;
		return category == other.category && content_no == other.content_no && Objects.equals(url, other.url);
	}
}
