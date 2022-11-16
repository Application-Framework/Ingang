package com.spring.ex.dao.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseVideoDTO;

@Repository
public class CourseVideoDAOImpl implements CourseVideoDAO {

	@Inject
	private SqlSession sqlSession;
	
	private final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public int submitCourseVideo(CourseVideoDTO dto) {
		return sqlSession.insert(namespace + ".submitCourseVideo", dto);
	}

	@Override
	public List<CourseVideoDTO> getCourseVideoList(int oli_no) {
		return sqlSession.selectList(namespace + ".getCourseVideoList", oli_no);
	}

	@Override
	public CourseVideoDTO getCourseVideo(int olv_no) {
		return sqlSession.selectOne(namespace + ".getCourseVideo", olv_no);
	}

	@Override
	public int deleteCourseVideo(int oli_no) {
		return sqlSession.delete(namespace + ".deleteCourseVideo", oli_no);
	}

	@Override
	public int updateCourseVideo(CourseVideoDTO dto) {
		return sqlSession.update(namespace + ".updateCourseVideo", dto);
	}

	@Override
	public int deleteNotContainedCourseVideo(int oli_no, int[] olv_noList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oli_no", oli_no);
		map.put("olv_noList", olv_noList);
		return sqlSession.delete(namespace + ".deleteNotContainedCourseVideo", map);
	}

}
