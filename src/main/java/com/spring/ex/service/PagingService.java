package com.spring.ex.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.spring.ex.dto.PagingDTO;

public class PagingService {
	private PagingDTO paging;
	private int nowPage;
	public PagingService(HttpServletRequest request, int totalCount, int pageSize) {
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		
		paging = new PagingDTO();
		paging.setPageNo(page);
		paging.setPageSize(pageSize);
		paging.setTotalCount(totalCount);
		nowPage = (page - 1) * pageSize;
		
		/*
		map = new HashMap<String, Integer>(); 
		map.put("Page", nowPage);
		map.put("PageSize", pageSize);
		*/
	}
	
	public PagingDTO getPaging() {
		return paging;
	}
	
	public int getNowPage() {
		return nowPage;
	}
}
