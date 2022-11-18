package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseRequestDTO;

@Service
public interface AdminCourseService {
	// 강의 게시판 가져오기
	public List<Map<String, Object>> getCourseBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize);
	
	// 강의 게시물 총 개수 가져오기
	public int getCoursePostCount(String searchCategory, String searchKeyword);
	
	// 강사의 강의 게시판 가져오기
	public List<Map<String, Object>> getTeacherCourseBoard(int olt_no, String searchCategory, String searchKeyword, int nowPage, int pageSize);
	
	// 강의 게시물 총 개수 가져오기
	public int getTeacherCoursePostCount(int olt_no, String searchCategory, String searchKeyword);
	
	// 요청없이 바로 생성
	public int createCourse(CourseDTO course, MultipartFile thumbnail, String[] categorys, String[] tags, String[] videoTitles, String[] videoPaths);
	
	// 요청없이 바로 수정
	public int updateCourse(CourseDTO course, MultipartFile thumbnail, String[] categorys, String[] tags, String[] olv_noList, String[] videoTitles, String[] videoPaths);
	
	//-------------------------------
	// 강의 요청 부분
	//-------------------------------
	public List<Map<String, Object>> getPendingCourseRequestList(String searchCategory, String search, int nowPage, int pageSize);
	public int getPendingCoursesCount();
	public CourseRequestDTO getCourseRequest(int olr_no);
	public CourseRequestDTO getCourseRequestByOli_no(int oli_no);
	public int insertCourseRequest(CourseRequestDTO dto);
	public int updateCourseRequest(CourseRequestDTO dto);
	public int deleteCourseRequest(int olr_no);
	
	// 관리자의 강의 승인
	public int approveCourse(int olr_no);
	
	// 관리자의 강의 거절
	public int rejectCourse(int olr_no, String rejection_message);
	
	// 모든 강의 가져오기
	public List<CourseDTO> getAllCourseList();
}
