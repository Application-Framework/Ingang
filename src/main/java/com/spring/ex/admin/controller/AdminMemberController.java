package com.spring.ex.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.admin.service.AdminMemberService;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.service.PagingService;

@Controller 
public class AdminMemberController {
	
	@Inject AdminMemberService service;
	
	private PagingService pagingService;
	
	//관리자 페이지 회원 메인페이지
	@RequestMapping(value = "/admin/memberlist", method = RequestMethod.GET)
	public String adminMemberMain(Model model, HttpServletRequest request) throws Exception {
		String searchKeyword = request.getParameter("searchKeyword");
		HashMap<String, Object> map = new HashMap<String, Object>(); 
		
		
		pagingService = new PagingService(request, service.getMemberTotalCount(map) ,10);
		map.put("Page", pagingService.getNowPage());
		map.put("PageSize", 10);
		
		List<MemberDTO> adminMemberList = service.getMemberList(map);
		
		model.addAttribute("adminMemberList", adminMemberList);
		model.addAttribute("Paging", pagingService.getPaging());
		
		return "admin/member/memberlist";
	}
	
}