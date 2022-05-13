package com.spring.ex.service;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.MemberDTO;
@Service
public interface MemberService {
	
	//회원 가입
	public void signUp(MemberDTO dto) throws Exception;
	
	// 일반 회원 아이디 중복 체크
	public MemberDTO memberIdCheck(String m_id) throws Exception;
	
	//로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
}
