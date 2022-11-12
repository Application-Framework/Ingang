package com.spring.ex.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminNoteDAO {
	public List<Map<String, Object>> getAdminNoteBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize);
	public int getAdminNotePostCount(String searchCategory, String searchKeyword);
}
