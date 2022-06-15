package com.spring.ex.dao.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;

@Repository
public interface CourseTagDAO {
	
	// 강좌의 태그 가져오기
	public List<CourseTagDTO> getCourseTags(int oli_no);
	
	// 강의에 태그 등록
	public int submitTag(CourseTagDTO dto);
}
