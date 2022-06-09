package com.spring.ex.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.ex.dto.CourseReplyDTO;

@Repository
public interface LiveCourseReplyDAO {
	
	// 실시간 댓글
	public List<CourseReplyDTO> live_replyList() throws Exception;
	
}
