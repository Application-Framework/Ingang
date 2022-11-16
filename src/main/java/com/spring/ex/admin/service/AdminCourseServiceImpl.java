package com.spring.ex.admin.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.admin.dao.AdminCourseDAO;
import com.spring.ex.dao.course.CourseDAO;
import com.spring.ex.dao.course.CourseRequestDAO;
import com.spring.ex.dao.course.CourseSubTypeDAO;
import com.spring.ex.dao.course.CourseTagDAO;
import com.spring.ex.dao.course.CourseVideoDAO;
import com.spring.ex.dao.note.NoteArticleDAO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseRequestDTO;
import com.spring.ex.dto.course.CourseSubTypeDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.dto.note.NoteArticleDTO;
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
	
	@Inject
	private CourseRequestDAO courseRequestDAO;
	
	@Inject
	private NoteArticleDAO noteArticleDAO;
	
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
			cv.setOrder(i+1);
			courseVideoDAO.submitCourseVideo(cv);
		}

		// 강의 요청 대기열에 등록
		CourseRequestDTO cr = new CourseRequestDTO();
		cr.setOli_no(course.getOli_no());
		cr.setOrigin_oli_no(course.getOli_no());
		cr.setApproval_status(1);
		courseRequestDAO.insertCourseRequest(cr);
		System.out.println("cr : " + cr);
		
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

	@Override
	public int updateCourse(CourseDTO course, MultipartFile thumbnail, String[] categorys, String[] tags, String[] olv_noList, String[] videoTitles, String[] videoPaths) {
		if(!thumbnail.isEmpty()) {
			// 기존 강의 표지 삭제
			if(course.getImg_path() != null) fileService.deleteFileToLocalAndServer(course.getImg_path());
			
			// 새로운 강의 표지 등록
			String img_path = fileService.insertFileToLocalAndServer(thumbnail, courseImagePath);
			course.setImg_path(img_path);
		}
		
		courseDAO.updateCourse(course);
		
		courseSubTypeDAO.deleteCourseSubType(course.getOli_no());
		for(String category : categorys) {
			CourseSubTypeDTO cst = new CourseSubTypeDTO();
			cst.setOli_no(course.getOli_no());
			cst.setSub_type_no(Integer.parseInt(category));
			courseSubTypeDAO.insertCourseSubType(cst);
		}
		
		courseTagDAO.deleteCourseTag(course.getOli_no());
		for(String tag : tags) {
			CourseTagDTO ct = new CourseTagDTO();
			ct.setOli_no(course.getOli_no());
			ct.setTag_no(Integer.parseInt(tag));
			courseTagDAO.submitCourseTag(ct);
		}
		
		int[] _olv_noList = new int[videoTitles.length];
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO cv;
			int _i = i;
			if(!olv_noList[i].equals("신규") && courseVideoDAO.getCourseVideoList(course.getOli_no()).stream().filter(video -> video.getOlv_no() == Integer.parseInt(olv_noList[_i])).count() != 0) {
				System.out.println("count() : " + courseVideoDAO.getCourseVideoList(course.getOli_no()).stream().filter(video -> video.getOlv_no() == Integer.parseInt(olv_noList[_i])).count()); 
				cv = courseVideoDAO.getCourseVideo(Integer.parseInt(olv_noList[i]));
				cv.setTitle(videoTitles[i]);
				cv.setS_file_name(videoPaths[i]);
				cv.setOrder(i+1);
				courseVideoDAO.updateCourseVideo(cv);
			}
			else {
				cv = new CourseVideoDTO();
				cv.setOli_no(course.getOli_no());
				cv.setTitle(videoTitles[i]);
				cv.setS_file_name(videoPaths[i]);
				cv.setOrder(i+1);
				courseVideoDAO.submitCourseVideo(cv);
			}
			_olv_noList[i] = cv.getOlv_no();
		}
		courseVideoDAO.deleteNotContainedCourseVideo(course.getOli_no(), _olv_noList);
		
		/*
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO cv = new CourseVideoDTO();
			cv.setOli_no(course.getOli_no());
			cv.setTitle(videoTitles[i]);
			cv.setS_file_name(videoPaths[i]);
			courseVideoDAO.submitCourseVideo(cv);
			//List<NoteArticleDTO> nal = noteArticleDAO.getNoteArticleListByOlv_no(cv.getOlv_no());
			//for
			//noteArticleDAO.updateNoteArticle(null); cv.getOlv_no();
		}*/

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
	
	@Override
	public List<Map<String, Object>> getPendingCourseRequestList(String searchCategory, String search, int nowPage, int pageSize) {
		return courseRequestDAO.selectListPendingCourseRequest(searchCategory, search, nowPage, pageSize);
	}

	@Override
	public int getPendingCoursesCount() {
		return courseRequestDAO.getPendingCoursesCount();
	}

	@Override
	public CourseRequestDTO getCourseRequest(int olr_no) {
		return courseRequestDAO.selectOneCourseRequest(olr_no);
	}

	@Override
	public CourseRequestDTO getCourseRequestByOli_no(int oli_no) {
		return courseRequestDAO.getCourseRequestByOli_no(oli_no);
	}
	
	@Override
	public int insertCourseRequest(CourseRequestDTO dto) {
		return courseRequestDAO.insertCourseRequest(dto);
	}

	@Override
	public int updateCourseRequest(CourseRequestDTO dto) {
		return courseRequestDAO.updateCourseRequest(dto);
	}

	@Override
	public int deleteCourseRequest(int olr_no) {
		return courseRequestDAO.deleteCourseRequest(olr_no);
	}

	@Override
	public int approveCourse(int olr_no) {
		CourseRequestDTO cr = getCourseRequest(olr_no);
		cr.setApproval_status(1);
		
		// 신규등록이면 복제 과정을 안거쳐도 됨. 수정일 때만 원본 <- 요청본 복제
		if(cr.getOli_no() != cr.getOrigin_oli_no()) {
			CourseDTO currentCourse = courseDAO.getCourseDetail(cr.getOli_no());
			CourseDTO originCourse = courseDAO.getCourseDetail(cr.getOrigin_oli_no());
			
			// 강의 수정
			originCourse.setTitle(currentCourse.getTitle());
			originCourse.setContent(currentCourse.getContent());
			originCourse.setIntroduction(currentCourse.getIntroduction());
			originCourse.setImg_path(currentCourse.getImg_path());
			originCourse.setPrice(currentCourse.getPrice());
			originCourse.setLevel(currentCourse.getLevel());
			originCourse.setUpdate_date(new Date(System.currentTimeMillis()));
			courseDAO.updateCourse(originCourse);
			
			// 강의 서브 카테고리 복제
			courseSubTypeDAO.deleteCourseSubType(originCourse.getOli_no());
			for(CourseSubTypeDTO cst : courseSubTypeDAO.getCourseSubTypeList(currentCourse.getOli_no())) {
				cst.setOli_no(originCourse.getOli_no());
				courseSubTypeDAO.insertCourseSubType(cst);
			}
			courseSubTypeDAO.deleteCourseSubType(currentCourse.getOli_no());
			
			// 강의 태그 복제
			courseTagDAO.deleteCourseTag(originCourse.getOli_no());
			for(CourseTagDTO ct : courseTagDAO.getCourseTags(currentCourse.getOli_no())) {
				ct.setOli_no(originCourse.getOli_no());
				courseTagDAO.submitCourseTag(ct);
			}
			courseTagDAO.deleteCourseTag(currentCourse.getOli_no());
			
			// 강의 동영상 복제
			courseVideoDAO.deleteCourseVideo(originCourse.getOli_no());
			for(CourseVideoDTO cv : courseVideoDAO.getCourseVideoList(currentCourse.getOli_no())) {
				cv.setOli_no(originCourse.getOli_no());
				courseVideoDAO.submitCourseVideo(cv);
			}
			courseVideoDAO.deleteCourseVideo(currentCourse.getOli_no());
			
			// 파일 관리 테이블의 content_no 바꾸기
			fileService.changeContent_no(currentCourse.getOli_no(), originCourse.getOli_no());
		}
		
		updateCourseRequest(cr);
		return 1;
	}

	// 강의 요청 거절
	@Override
	public int rejectCourse(int olr_no, String rejection_message) {
		CourseRequestDTO cr = getCourseRequest(olr_no);
		cr.setApproval_status(-1);
		cr.setRejection_message(rejection_message);
		updateCourseRequest(cr);
		return 1;
	}
	
	// 모든 강의 가져오기
	@Override
	public List<CourseDTO> getAllCourseList() {
		return adminCourseDAO.getAllCourseList();
	}
}
