package com.spring.ex.dao;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CourseVideoDTO;

@Repository
public interface CourseVideoDAO {
	// 강의 비디오 등록
	public int submitCourseVideo(CourseVideoDTO dto);
}
