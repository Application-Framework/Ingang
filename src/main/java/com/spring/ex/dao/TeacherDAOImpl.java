package com.spring.ex.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TeacherDTO;

@Repository
public class TeacherDAOImpl implements TeacherDAO{
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.TeacherMapper";
	
	@Override
	public TeacherDTO getTeacherInfo(int olt_no) {
		return sqlSession.selectOne(namespace + ".getTeacherInfo", olt_no);
	}

	@Override
	public TeacherDTO getTeacherInfoByM_no(int m_no) {
		return sqlSession.selectOne(namespace + ".getTeacherInfoByM_no", m_no);
	}
	
	@Override
	public int insertCourseTeacher(TeacherDTO dto) {
		return sqlSession.insert(namespace + ".insertCourseTeacher", dto);
	}

	@Override
	public int updateCourseTeacher(TeacherDTO dto) {
		return sqlSession.update(namespace + ".updateCourseTeacher", dto);
	}
	
	@Override
	public int deleteCourseTeacher(int olt_no) {
		return sqlSession.delete(namespace + ".deleteCourseTeacher", olt_no);
	}

	@Override
	public int checkTeacherByM_no(int m_no) {
		return sqlSession.selectOne(namespace + ".checkTeacherByM_no", m_no);
	}
}
