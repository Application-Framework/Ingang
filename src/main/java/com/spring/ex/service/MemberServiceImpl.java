package com.spring.ex.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.MemberDAO;
import com.spring.ex.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	//회원가입
	@Override
	public void signUp(MemberDTO dto) throws Exception {
		dao.signUp(dto);
	}
	
	// 아이디 중복 체크
	@Override
	public MemberDTO idCheck(String m_id) throws Exception {
		return dao.idCheck(m_id);
	}

	//로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return dao.login(dto);
	}
	
	// 회원 정보수정
	@Override
	public void update(MemberDTO memberDTO) throws Exception {
		dao.update(memberDTO);
	}
	
	// 회원 탈퇴
	@Override
	public void delete(MemberDTO memberDTO) throws Exception {
		dao.delete(memberDTO);
	}
	
	// 강의 구매 건수
	@Override
	public Integer countMyCourse(Integer m_no) throws Exception {
		return dao.countMyCourse(m_no);
	}
	
	// 노트 구매 건수
	@Override
	public Integer countMyNote(Integer m_no) throws Exception {
		return dao.countMyNote(m_no);
	}
	
	// 게시글 작성 건수
	@Override
	public Integer countMyPost(Integer m_no) throws Exception {
		return dao.countMyPost(m_no);
	}

	@Override
	public String getNameByM_no(int m_no) throws Exception {
		return dao.getNameByM_no(m_no);
	}
}
