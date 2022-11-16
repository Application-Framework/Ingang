package com.spring.ex.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.TeacherRequestDTO;

@Repository
public class AdminTeacherDAOImpl implements AdminTeacherDAO {
	
	@Inject
	private SqlSession sql;
	
	private final String namespace = "com.spring.ex.AdminTeacherMapper"; 
		
	// 강사 게시판 가져오기
	@Override
	public List<Map<String, Object>> getAdminTeacherBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		map.put("nowPage", nowPage);
		map.put("pageSize", pageSize);
		
		return sql.selectList(namespace + ".getAdminTeacherBoard", map);
	}

	// 강사 목록 총 개수 가져오기
	@Override
	public int getAdminTeacherTotalCount(String searchCategory, String searchKeyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		
		return sql.selectOne(namespace + ".getAdminTeacherTotalCount", map);
	}

	// 승인 대기 강사 게시판 가져오기
	@Override
	public List<Map<String, Object>> getAdminPendingTeacherBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		map.put("nowPage", nowPage);
		map.put("pageSize", pageSize);
		
		return sql.selectList(namespace + ".getAdminPendingTeacherBoard", map);
	}

	// 승인 대기 강사 목록 총 개수 가져오기
	@Override
	public int getAdminPendingTeacherTotalCount(String searchCategory, String searchKeyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		
		return sql.selectOne(namespace + ".getAdminPendingTeacherTotalCount", map);
	}
	
	// 강사 정보 가져오기
	@Override
	public TeacherDTO getAdminTeacherInfo(int olt_no) {
		return sql.selectOne(namespace + ".getAdminTeacherInfo", olt_no);
	}

	// 승인 대기강사 정보 가져오기
	@Override
	public TeacherRequestDTO getAdminPendingTeacherInfo(int oltr_no) {
		return sql.selectOne(namespace + ".getAdminPendingTeacherInfo", oltr_no);
	}
	
	// 강사 추가
	@Override
	public int insertTeacher(TeacherDTO dto) {
		return sql.insert(namespace  + ".insertTeacher", dto);
	}

	// 강사 수정
	@Override
	public int updateTeacher(TeacherDTO dto) {
		return sql.update(namespace + ".updateTeacher", dto);
	}

	// 강사 삭제
	@Override
	public int deleteTeacher(int olt_no) {
		return sql.delete(namespace + ".deleteTeacher", olt_no);
	}

	
}
