package com.spring.ex.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.TeacherRequestDTO;

@Repository
public class TeacherRequestDAOImpl implements TeacherRequestDAO {

	@Inject
	private SqlSession sql;
	
	private final String namespace = "com.spring.ex.TeacherRequestMapper";
	
	@Override
	public int insertTeacherRequest(TeacherRequestDTO dto) {
		return sql.insert(namespace + ".insertTeacherRequest", dto);
	}

	@Override
	public int updateTeacherRequest(TeacherRequestDTO dto) {
		return sql.update(namespace + ".updateTeacherRequest", dto);
	}
	
}
