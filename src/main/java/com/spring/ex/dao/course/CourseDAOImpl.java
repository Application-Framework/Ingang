package com.spring.ex.dao.course;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseFileUploadDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;

@Repository
public class CourseDAOImpl implements CourseDAO {
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.spring.ex.CourseMapper";
	
	@Override
	public List<HashMap<String, Object>> getCoursePage(HashMap<String, Object> map) {
		return sqlSession.selectList(namespace + ".getCoursePage", map);
	}

	@Override
	public int getCourseTotalCount(HashMap<String, Object> map) {
		return sqlSession.selectOne(namespace + ".getCourseTotalCount", map);
	}

	@Override
	public CourseDTO getCourseDetail(int oli_no) {
		return sqlSession.selectOne(namespace + ".getCourseDetail", oli_no);
	}
	
	@Override
	public int insertCourse(CourseDTO dto) {
		return sqlSession.insert(namespace + ".insertCourseCourse", dto);
	}

	@Override
	public int updateCourse(CourseDTO courseDTO, List<CourseTagDTO> courseTagList, List<CourseVideoDTO> courseVideoList) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 추가해야 함
		return sqlSession.update(namespace + ".updateCourse", map);
	}
	
	@Override
	public int deleteCourse(int oli_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteCourse", oli_no);
	}

	@Override
	public List<CourseFileUploadDTO> selectFileListByOli_no(int oli_no) throws Exception {
		return sqlSession.selectList(namespace + ".selectFileListByOli_no", oli_no);
	}

	@Override
	public int insertFile(CourseFileUploadDTO dto) throws Exception {
		return sqlSession.insert(namespace + ".insertFile", dto);
	}

	@Override
	public int deleteFileByUrl(String url) throws Exception {
		return sqlSession.delete(namespace + ".deleteFileByUrl", url);
	}
}
