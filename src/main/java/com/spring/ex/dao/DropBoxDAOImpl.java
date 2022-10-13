package com.spring.ex.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.HistoryOrderLectureDTO;

@Repository
public class DropBoxDAOImpl implements DropBoxDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.mappers.dropBoxMapper";
	
	// 드롭박스 관심 강의 내역
	@Override
	public List<HistoryOrderLectureDTO> myInterestedCourseDropBox(Integer m_no) throws Exception {
		return sqlSession.selectList(namespace + ".myInterestedCourseDropBox", m_no);
	}
	
	// 드롭박스 관심 강의 갯수
	@Override
	public Integer myInterestedCourseCountDropBox(Integer m_no) throws Exception {
		return sqlSession.selectOne(namespace + ".myInterestedCourseCountDropBox", m_no);
	}
}
