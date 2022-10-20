package com.spring.ex.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminNoteController {
	
	// 노트 관리 대시보드 페이지
	@RequestMapping("/admin/note")
	public String noteDashboard() {
		
		return "admin/note/note-dashboard";
	}
	
	// 승인 대기중인 강의들 관리 페이지
	@RequestMapping("/admin/note/pending-notes")
	public String pendingNotes() {
		
		return "admin/note/pending-notes";
	}
}
