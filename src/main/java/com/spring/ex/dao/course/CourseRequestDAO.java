package com.spring.ex.dao.course;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseRequestDTO;

@Repository
public interface CourseRequestDAO {
	public List<Map<String, Object>> selectListPendingCourseRequest(String searchCategory, String search, int nowPage, int pageSize);
	public int getPendingCoursesCount();
	public CourseRequestDTO selectOneCourseRequest(int olr_no);
	public CourseRequestDTO getCourseRequestByOli_no(int oli_no);
	public int insertCourseRequest(CourseRequestDTO dto);
	public int updateCourseRequest(CourseRequestDTO dto);
	public int deleteCourseRequest(int olr_no);
}
