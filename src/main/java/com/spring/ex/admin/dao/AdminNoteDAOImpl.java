package com.spring.ex.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AdminNoteDAOImpl implements AdminNoteDAO {

	@Inject
	private SqlSession sql;
	
	private final String namespace = "com.spring.ex.AdminNoteMapper";
	
	@Override
	public List<Map<String, Object>> getAdminNoteBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		map.put("nowPage", nowPage);
		map.put("pageSize", pageSize);
		
		return sql.selectList(namespace + ".getAdminNoteBoard", map);
	}

	@Override
	public int getAdminNotePostCount(String searchCategory, String searchKeyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		
		return sql.selectOne(namespace + ".getAdminNotePostCount", map);
	}
}
