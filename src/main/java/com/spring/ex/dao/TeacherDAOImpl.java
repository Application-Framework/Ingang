package com.spring.ex.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TeacherDTO;

@Repository
public class TeacherDAOImpl implements TeacherDAO{
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public TeacherDTO getTeacherInfo(int olt_no) {
		return sqlSession.selectOne(namespace + ".getTeacherInfo", olt_no);
	}

	@Override
	public int getOlt_noByM_no(int m_no) {
		return sqlSession.selectOne(namespace + ".getOlt_noByM_no", m_no);
	}
	
}
