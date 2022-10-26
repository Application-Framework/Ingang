package com.spring.ex.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.admin.dao.AdminMemberDAO;
import com.spring.ex.dto.MemberDTO;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {

	@Inject AdminMemberDAO adminDao;
	
	//회원목록 - 기본 + 검색 동시에 할 것
	public List<MemberDTO> getMemberList(HashMap<String, Object> map) throws Exception {
		return adminDao.getMemberList(map);
	}
	
	//회원 총 수
	public int getMemberTotalCount(HashMap<String, Object> map) throws Exception {
		return adminDao.getMemberTotalCount(map);
	}
	
	//회원 정보 상세조회
	public MemberDTO getMemberView(int m_no) throws Exception {
		return adminDao.getMemberView(m_no);
	}
	
	//회원정보 삭제
	public int deleteMember(int m_no) throws Exception {
		return adminDao.deleteMember(m_no);
	}
	
	//회원정보 강의 구매이력 출력
	public List<Map<String, Object>> getMemberOrderLecture(HashMap<String, Object> map) throws Exception {
		return adminDao.getMemberOrderLecture(map);
	}
	public int getMemberOrderLectureTotalCount(HashMap<String, Object> map) throws Exception {
		return adminDao.getMemberOrderLectureTotalCount(map);
	}
	
	//회원정보 노트 구매이력 출력
	public List<Map<String, Object>> getMemberOrderNote(HashMap<String, Object> map) throws Exception {
		return adminDao.getMemberOrderNote(map);
	}
	public int getMemberOrderNoteTotalCount(HashMap<String, Object> map) throws Exception {
		return adminDao.getMemberOrderNoteTotalCount(map);
	}
	
}