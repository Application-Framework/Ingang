package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.dto.course.CourseDTO;

@Service
public interface AdminCourseService {
	// 강의 게시판 가져오기
	public List<Map<String, Object>> getCourseBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize);
	
	// 강의 게시물 총 개수 가져오기
	public int getCoursePostCount(String searchCategory, String searchKeyword);
	
	// 강의 요청없이 바로 생성
	public int createCourse(CourseDTO course, MultipartFile thumbnail, String[] categorys, String[] tags, String[] videoTitles, String[] videoPaths);
}
