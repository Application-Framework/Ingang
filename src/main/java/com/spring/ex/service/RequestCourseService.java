package com.spring.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.MainTypeDTO;
import com.spring.ex.dto.course.request.RequestCourseDTO;
import com.spring.ex.dto.course.request.RequestCourseSubTypeDTO;
import com.spring.ex.dto.course.request.RequestCourseTagDTO;
import com.spring.ex.dto.course.request.RequestCourseVideoDTO;

@Service
public interface RequestCourseService {
	
	//-----------------------------
	// 강의 부분
	//-----------------------------
	
	// 강의 등록/수정 요청 추가
	public int insertRequestCourse(RequestCourseDTO dto);
	
	// 강의 등록/수정 요청 수정
	public int updateRequestCourse(RequestCourseDTO dto);
	
	// 강의 등록/수정 요청 승인
	public int approveRequestCourse(int olr_no);
	
	// 강의 등록/수정 요청 거절
	public int rejectRequestCourse(int olr_no, String rejectMessage);
	
	// 승인 상태에 따라 강의 요청 튜플들 가져오기
	public List<RequestCourseDTO> getRequestCourseList(int approval);
	
	
	//-----------------------------
	// 강의 서브 카테고리 부분
	//-----------------------------
	
	// 강의의 서브 카테고리 리스트 가져오기
	public List<RequestCourseSubTypeDTO> getRequestCourseSubTypeList(int roli_no);
	
	// 강의에 서브 카테고리 등록
	public int submitRequestCourseSubType(RequestCourseSubTypeDTO dto);
	
	// 강의의 서브 카테고리 삭제
	public int deleteRequestCourseSubType(int roli_no);
	
	// 강좌의 메인 카테고리 가져오기
	public MainTypeDTO getMainTypeOfCourse(int roli_no);
	
	
	//-----------------------------
	// 강의 태그 부분
	//-----------------------------
	
	
	// 강의에 태그 등록
	public int submitRequestCourseTag(RequestCourseTagDTO dto);
	
	// 강의 태그 삭제
	public int deleteRequestCourseTag(int roli_no);
	
	// 태그 리스트에 태그가 있는지 확인
	public boolean containsInTagList(List<RequestCourseTagDTO> tagList, String tag_abbr);
	
	// 카테고리 리스트에 카테고리가 있는지 확인
	public boolean containsInCategoryList(List<RequestCourseSubTypeDTO> categoryList, String tag_abbr);
	
	
	//-----------------------------
	// 강의 비디오 부분
	//-----------------------------
	
	// 강의 비디오 등록
	public int submitRequestCourseVideo(RequestCourseVideoDTO dto);
	
	// 강의 비디오 삭제
	public int deleteRequestCourseVideo(int roli_no);
	
	// 강의 비디오 리스트 가져오기
	public List<RequestCourseVideoDTO> getRequestCourseVideoList(int roli_no);
	
	// 강의 비디오 가져오기
	public RequestCourseVideoDTO getRequestCourseVideo(int rolv_no);
	
}
