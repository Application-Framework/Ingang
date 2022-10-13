package com.spring.ex.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	//mybatis
	@Inject
	private SqlSession sql;
	
	//mapper
	private static final String namespcae = "com.spring.mappers.memberMapper";
	
	//회원가입
	@Override
	public void signUp(MemberDTO dto) throws Exception {
		sql.insert(namespcae + ".signUp", dto);
	}
	
	// 아이디 중복 체크
	@Override
	public MemberDTO idCheck(String m_id) throws Exception {
		return sql.selectOne(namespcae + ".idCheck", m_id);
	}
	
	// 비밀번호 체크
	@Override
	public MemberDTO pwCheck(MemberDTO memberDTO) throws Exception {
		return sql.selectOne(namespcae + ".pwCheck", memberDTO);
	}

	//로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return sql.selectOne(namespcae + ".login", dto);
	}
	
	// 회원 정보수정
	@Override
	public void update(MemberDTO memberDTO) throws Exception {
		sql.update(namespcae + ".update", memberDTO);
	}
	
	// 회원 탈퇴
	@Override
	public void delete(MemberDTO memberDTO) throws Exception {
		sql.delete(namespcae + ".delete", memberDTO);
	}
	
	// 강의 구매 건수
	@Override
	public Integer countMyCourse(Integer m_no) throws Exception {
		return sql.selectOne(namespcae + ".countMyCourse", m_no);
	}
	
	// 노트 구매 건수
	@Override
	public Integer countMyNote(Integer m_no) throws Exception {
		return sql.selectOne(namespcae + ".countMyNote", m_no);
	}
	
	// 게시글 작성 건수
	@Override
	public Integer countMyPost(Integer m_no) throws Exception {
		return sql.selectOne(namespcae + ".countMyPost", m_no);
	}
	
	@Override
	public MemberDTO getMemberByM_no(int m_no) throws Exception {
		return sql.selectOne(namespcae + ".getMemberByM_no", m_no);
	}
}
