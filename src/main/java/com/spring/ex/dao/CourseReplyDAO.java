package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.dto.course.CourseTagDTO;

@Repository
public interface CourseReplyDAO {
	
	// 리뷰 가져오기
	public List<CourseReplyDTO> getCourseReplys(int oli_no);

	// 강의에 리뷰 등록
	public int submitReply(CourseReplyDTO dto);
}
