package com.spring.ex.dao.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseVideoDTO;

@Repository
public interface CourseVideoDAO {
	// 강의 비디오 등록
	public int submitCourseVideo(CourseVideoDTO dto);
	
	// 강의 비디오 리스트 가져오기
	public List<CourseVideoDTO> getCourseVideoList(int oli_no);
	
	// 강의 비디오 가져오기
	public CourseVideoDTO getCourseVideo(int olv_no);
}
