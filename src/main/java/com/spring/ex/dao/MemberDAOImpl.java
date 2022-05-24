package com.spring.ex.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
	public MemberDTO memberIdCheck(String m_id) throws Exception {
		return sql.selectOne(namespcae + ".memberIdCheck", m_id);
	}

	//로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return sql.selectOne(namespcae + ".login", dto);
	}

	@Override
	public String getNameByM_no(int m_no) throws Exception {
		return sql.selectOne(namespcae + ".getNameByM_no", m_no);
	}
}
