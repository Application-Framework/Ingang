package com.spring.ex.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.ex.dao.LiveCourseReplyDAO;
import com.spring.ex.dto.course.CourseReplyDTO;

@Service
public class LiveCourseReplyServiceImpl implements LiveCourseReplyService {
	
	@Inject
	private LiveCourseReplyDAO dao;
	
	// 실시간 댓글
	@Override
	public List<CourseReplyDTO> live_replyList() throws Exception {
		return dao.live_replyList();
	}
	
}