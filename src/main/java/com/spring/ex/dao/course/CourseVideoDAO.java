package com.spring.ex.dao.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseVideoDTO;

@Repository
public interface CourseVideoDAO {
	// 강의 비디오 등록
	public int submitCourseVideo(CourseVideoDTO dto);
	
	// 강의 비디오 리스트 가져오기
	public List<CourseVideoDTO> getCourseVideoList(int oli_no);
	
	// 강의 비디오 가져오기
	public CourseVideoDTO getCourseVideo(int olv_no);
	
	// 강의 비디오 가져오기
	public CourseVideoDTO getCourseVideoByOli_noAndOrder(int oli_no, int order);
	
	// 강의 비디오 삭제
	public int deleteCourseVideo(int oli_no);
	
	// 강의 비디오 수정
	public int updateCourseVideo(CourseVideoDTO dto);
	
	// olv_noList에 포함되어있지 않는 강의 비디오 삭제
	public int deleteNotContainedCourseVideo(int oli_no, int[] olv_noList);
}
