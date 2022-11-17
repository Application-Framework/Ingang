package com.spring.ex.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.MemberDTO;

@Service
public interface AdminMemberService {

	//회원목록 - 기본 + 검색 동시에 할 것
	public List<MemberDTO> getMemberList(HashMap<String, Object> map) throws Exception;
	
	//회원 총 수
	public int getMemberTotalCount(HashMap<String, Object> map) throws Exception;
	
	//회원 정보 상세조회
	public MemberDTO getMemberView(int m_no) throws Exception;
	
	//회원정보 삭제
	public int deleteMember(int m_no) throws Exception;
	
	// 모든 회원 가져오기
	public List<MemberDTO> getAllMemberList();
	
	//회원정보 강의 구매이력 출력
	public List<Map<String, Object>> getMemberOrderLecture(HashMap<String, Object> map) throws Exception;
	public int getMemberOrderLectureTotalCount(HashMap<String, Object> map) throws Exception;
	
	//회원정보 노트 구매이력 출력
	public List<Map<String, Object>> getMemberOrderNote(HashMap<String, Object> map) throws Exception;
	public int getMemberOrderNoteTotalCount(HashMap<String, Object> map) throws Exception;
	
	//회원정보 작성게시글 출력
	public List<Map<String, Object>> getMemberCommunity(HashMap<String, Object> map) throws Exception;
	public int getMemberCommunityTotalCount(HashMap<String, Object> map) throws Exception;
	
	//관리자페이지 회원 수정
	public int updateAdminMember(MemberDTO mDto) throws Exception;
	//관리자페이지 회원 가입
	public int signUpAdminMember(MemberDTO mDto) throws Exception;
}