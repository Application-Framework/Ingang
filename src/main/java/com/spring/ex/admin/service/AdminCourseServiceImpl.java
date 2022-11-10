package com.spring.ex.admin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.admin.dao.AdminCourseDAO;
import com.spring.ex.dao.course.CourseDAO;
import com.spring.ex.dao.course.CourseSubTypeDAO;
import com.spring.ex.dao.course.CourseTagDAO;
import com.spring.ex.dao.course.CourseVideoDAO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseSubTypeDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.service.FileService;

@Repository
public class AdminCourseServiceImpl implements AdminCourseService {

	@Inject
	private AdminCourseDAO adminCourseDAO;

	@Inject
	private CourseDAO courseDAO;
	
	@Inject
	private CourseSubTypeDAO courseSubTypeDAO;
	
	@Inject
	private CourseTagDAO courseTagDAO;
	
	@Inject
	private CourseVideoDAO courseVideoDAO;
	
	@Inject
	private FileService fileService;
	
	@Resource(name="courseImagePath")
	String courseImagePath;
	
	// 강의 게시판 가져오기
	@Override
	public List<Map<String, Object>> getCourseBoard(String searchCategory, String searchKeyword, int nowPage, int pageSize) {
		return adminCourseDAO.getCourseBoard(searchCategory, searchKeyword, nowPage, pageSize);
	}

	// 강의 게시물 총 개수 가져오기
	@Override
	public int getCoursePostCount(String searchCategory, String searchKeyword) {
		return adminCourseDAO.getCoursePostCount(searchCategory, searchKeyword);
	}

	// 강의 요청없이 바로 생성
	@Override
	public int createCourse(CourseDTO course, MultipartFile thumbnail, String[] categorys, String[] tags, String[] videoTitles, String[] videoPaths) {
		String img_path = null;
		if(!thumbnail.isEmpty()) { 
			img_path = fileService.insertFileToLocalAndServer(thumbnail, courseImagePath);
		}
		course.setImg_path(img_path);
		
		courseDAO.submitCourse(course);
		for(String category : categorys) {
			CourseSubTypeDTO cst = new CourseSubTypeDTO();
			cst.setOli_no(course.getOli_no());
			cst.setSub_type_no(Integer.parseInt(category));
			courseSubTypeDAO.insertCourseSubType(cst);
		}
		
		for(String tag : tags) {
			CourseTagDTO ct = new CourseTagDTO();
			ct.setOli_no(course.getOli_no());
			ct.setTag_no(Integer.parseInt(tag));
			courseTagDAO.submitCourseTag(ct);
		}
		
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO cv = new CourseVideoDTO();
			cv.setOli_no(course.getOli_no());
			cv.setTitle(videoTitles[i]);
			cv.setS_file_name(videoPaths[i]);
			courseVideoDAO.submitCourseVideo(cv);
		}

		// 게시글의 파일 관리
		try {
			fileService.manageFileAfterPostSubmission(course.getContent(), course.getOli_no(), 1); // category 강의 : 1
		}
		catch(Exception e) {
			System.err.println("강의 파일 저장 실패 : " + e.getMessage());
			return 0;
		}
		
		return 1;
	}
}
