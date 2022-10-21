package com.spring.ex.dao.course.request;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.request.RequestCourseDTO;

@Repository
public interface RequestCourseDAO {
	public int insertRequestCourse(RequestCourseDTO dto);
	public int updateRequestCourse(RequestCourseDTO dto);
	public RequestCourseDTO selectOneRequestCourse(int olr_no);
	public List<RequestCourseDTO> selectListRequestCourse(int approval);
}
