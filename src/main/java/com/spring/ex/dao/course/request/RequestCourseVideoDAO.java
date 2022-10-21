package com.spring.ex.dao.course.request;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.request.RequestCourseVideoDTO;

@Repository
public interface RequestCourseVideoDAO {
	// 강의 비디오 등록
	public int submitRequestCourseVideo(RequestCourseVideoDTO dto);
	
	// 강의 비디오 리스트 가져오기
	public List<RequestCourseVideoDTO> getRequestCourseVideoList(int roli_no);
	
	// 강의 비디오 가져오기
	public RequestCourseVideoDTO getRequestCourseVideo(int rolv_no);
	
	// 강의 비디오 삭제
	public int deleteRequestCourseVideo(int roli_no);
}
