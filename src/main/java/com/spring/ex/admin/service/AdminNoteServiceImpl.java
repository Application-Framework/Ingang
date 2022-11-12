package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.admin.dao.AdminNoteDAO;

@Service
public class AdminNoteServiceImpl implements AdminNoteService {

	@Inject
	private AdminNoteDAO adminNoteDAO;
	
	@Override
	public List<Map<String, Object>> getAdminNoteBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize) {
		return adminNoteDAO.getAdminNoteBoard(searchCategory, searchKeyword, nowPage, pageSize);
	}

	@Override
	public int getAdminNotePostCount(String searchCategory, String searchKeyword) {
		return adminNoteDAO.getAdminNotePostCount(searchCategory, searchKeyword);
	}
}
