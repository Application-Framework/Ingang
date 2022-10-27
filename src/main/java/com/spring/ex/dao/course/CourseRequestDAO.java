package com.spring.ex.dao.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseRequestDTO;

@Repository
public interface CourseRequestDAO {
	public List<CourseRequestDTO> selectListCourseRequest();
	public List<CourseRequestDTO> selectListCourseRequestByOli_no(int oli_no);
	public CourseRequestDTO selectOneCourseRequest(int olr_no);
	public int insertCourseRequest(CourseRequestDTO dto);
	public int updateCourseRequest(CourseRequestDTO dto);
	public int deleteCourseRequest(int olr_no);
}
