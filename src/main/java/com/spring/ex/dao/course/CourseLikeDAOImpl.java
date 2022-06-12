package com.spring.ex.dao.course;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CourseLikeDAOImpl implements CourseLikeDAO {
	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public int getCourseLikeCount(int oli_no) {
		return sqlSession.selectOne(namespace + ".getCourseLikeCount", oli_no);
	}

	@Override
	public int insertCourseLike(int oli_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("oli_no", oli_no);
		map.put("m_no", m_no);
		return sqlSession.insert(namespace + ".insertCourseLike", map);
	}

	@Override
	public int deleteCourseLike(int oli_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("oli_no", oli_no);
		map.put("m_no", m_no);
		return sqlSession.delete(namespace + ".deleteCourseLike", map);
	}

	@Override
	public int existCourseLike(int oli_no, int m_no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("oli_no", oli_no);
		map.put("m_no", m_no);
		return sqlSession.selectOne(namespace + ".existCourseLike", map);
	}
}
