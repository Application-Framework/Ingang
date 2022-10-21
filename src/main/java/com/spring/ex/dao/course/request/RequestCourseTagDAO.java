package com.spring.ex.dao.course.request;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.request.RequestCourseTagDTO;

@Repository
public interface RequestCourseTagDAO {
	
	// 강좌의 태그 가져오기
	public List<RequestCourseTagDTO> getRequestCourseTags(int roli_no);
	
	// 강의에 태그 등록
	public int submitRequestCourseTag(RequestCourseTagDTO dto);
	
	// 강의 태그 삭제
	public int deleteRequestCourseTag(int roli_no);
}
