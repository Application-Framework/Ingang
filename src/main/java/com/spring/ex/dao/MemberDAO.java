package com.spring.ex.dao;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.MemberDTO;

@Repository
public interface MemberDAO {
	
	//회원 가입
	public void signUp(MemberDTO dto) throws Exception;
	
	// 아이디 중복 체크
	public MemberDTO idCheck(String m_id) throws Exception;
	
	//로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
	
	// 회원 정보수정
	public void update(MemberDTO memberDTO) throws Exception;
	
	// 회원 탈퇴
	public void delete(MemberDTO memberDTO) throws Exception;
	
	// 강의 구매 건수
	public Integer countMyCourse(Integer m_no) throws Exception;
	
	// 노트 구매 건수
	public Integer countMyNote(Integer m_no) throws Exception;
	
	// 게시글 작성 건수
	public Integer countMyPost(Integer m_no) throws Exception;
	
	// primary key로 이름 가져오기
	public String getNameByM_no(int m_no) throws Exception;
}
