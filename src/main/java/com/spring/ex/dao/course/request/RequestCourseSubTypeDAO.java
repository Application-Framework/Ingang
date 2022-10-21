package com.spring.ex.dao.course.request;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.request.RequestCourseSubTypeDTO;

@Repository
public interface RequestCourseSubTypeDAO {
	// 강의의 카테고리 가져오기
	public List<RequestCourseSubTypeDTO> getRequestCourseSubTypeList(int roli_no);
	
	// 강의에 카테고리 등록
	public int insertRequestCourseSubType(RequestCourseSubTypeDTO dto);
	
	// 강의 카테고리 삭제
	public int deleteRequestCourseSubType(int roli_no);
}
