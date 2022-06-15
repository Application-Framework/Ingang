package com.spring.ex.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.dto.course.HistoryOrderLectureDTO;

@Service
public interface CourseService {
	// 페이지의 게시물 가져오기
	public List<HashMap<String, Object>> getCoursePage(HashMap<String, Object> map);
	
	// 페이지 개수 가져오기
	public int getCourseTotalCount(HashMap<String, Object> map);
	
	// 강의 상세 가져오기
	public CourseDTO getCourseDetail(int pageNo);
	
	// 리뷰 가져오기
	public List<CourseReplyDTO> getCourseReplys(int oli_no);
	
	// 강좌의 태그 가져오기
	public List<CourseTagDTO> getCourseTags(int oli_no);
	
	// 강사 정보 가져오기
	public TeacherDTO getTeacherInfo(int olt_no);
	
	// 좋아요 개수 가져오기
	public int getCourseLikeCount(int oli_no);
	
	// 좋아요 추가
	public int insertCourseLike(int oli_no, int m_no);
	
	// 좋아요 삭제
	public int deleteCourseLike(int oli_no, int m_no);
	
	// 좋아요가 존재하는지
	public int existCourseLike(int oli_no, int m_no);
	
	// 강의 등록
	public int submitCourse(CourseDTO dto);
	
	// 강의에 리뷰 등록
	public int submitReply(CourseReplyDTO dto);
	
	// 강의에 태그 등록
	public int submitTag(CourseTagDTO dto);
	
	// 강의 비디오 등록
	public int submitCourseVideo(CourseVideoDTO dto);
	
	public int getOlt_noByM_no(int m_no);
	
	// 강의 비디오 리스트 가져오기
	public List<CourseVideoDTO> getCourseVideoList(int oli_no);
	
	// 강의 비디오 가져오기
	public CourseVideoDTO getCourseVideo(int olv_no);
	
	// 강의 구매 기록 등록
	public int insertHistoryOrderLecture(HistoryOrderLectureDTO dto);

	// 강의 구매 기록 가져오기
	public HistoryOrderLectureDTO getHistoryOrderLectureByOli_noM_no(int oli_no, int m_no);
	
	// 강의 구매 기록 리스트 가져오기
	public List<HistoryOrderLectureDTO> getHistoryOrderLectureList(int m_no);
}
