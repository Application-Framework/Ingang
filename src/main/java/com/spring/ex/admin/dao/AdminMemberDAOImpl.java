package com.spring.ex.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.MemberDTO;

@Repository
public class AdminMemberDAOImpl implements AdminMemberDAO {

	@Inject SqlSession sqlSession;
	private static final String namespaceAdmin = "com.spring.ex.AdminMemberMapper";
	
	//회원목록 - 기본 + 검색 동시에 할 것
	public List<MemberDTO> getMemberList(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespaceAdmin + ".getMemberList" , map);
	}
	
	//회원 총 수
	public int getMemberTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespaceAdmin + ".getMemberTotalCount", map);
	}
	
	//회원 정보 상세조회
	public MemberDTO getMemberView(int m_no) throws Exception {
		return sqlSession.selectOne(namespaceAdmin + ".getMemberView", m_no);
	}
	
	//회원정보 삭제
	public int deleteMember(int m_no) throws Exception {
		return sqlSession.delete(namespaceAdmin + ".deleteMember", m_no);
	}
	
	//회원정보 강의 구매이력 출력
	public List<Map<String, Object>> getMemberOrderLecture(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespaceAdmin + ".getMemberOrderLecture", map);
	}
	public int getMemberOrderLectureTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespaceAdmin + ".getMemberOrderLectureTotalCount", map);
	}
	
	//회원정보 노트 구매이력 출력
	public List<Map<String, Object>> getMemberOrderNote(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespaceAdmin + ".getMemberOrderNote", map);
	}
	public int getMemberOrderNoteTotalCount(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespaceAdmin + ".getMemberOrderNoteTotalCount", map);
	}
}