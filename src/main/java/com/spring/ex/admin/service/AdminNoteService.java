package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface AdminNoteService {
	public List<Map<String, Object>> getAdminNoteBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize);
	public int getAdminNotePostCount(String searchCategory, String searchKeyword);
}
