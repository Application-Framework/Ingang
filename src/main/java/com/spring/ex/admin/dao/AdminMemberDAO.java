package com.spring.ex.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.MemberDTO;

@Repository
public interface AdminMemberDAO {
	
	//회원목록 - 기본 + 검색 동시에 할 것
	public List<MemberDTO> getMemberList(HashMap<String, Object> map) throws Exception;
	
	//회원 총 수
	public int getMemberTotalCount(HashMap<String, Object> map) throws Exception;
	
	//회원 정보 상세조회
	public Map<String, Object> getMemberView(int m_no) throws Exception;
	
	//회원정보 삭제
	public void deleteMember(int m_no) throws Exception;
	
	// 모든 회원 가져오기
	public List<MemberDTO> getAllMemberList();
}