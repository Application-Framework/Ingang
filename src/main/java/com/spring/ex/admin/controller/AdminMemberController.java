package com.spring.ex.admin.controller;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.admin.service.AdminMemberService;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.service.PagingService;

@Controller 
public class AdminMemberController {
	
	@Inject AdminMemberService service;
	
	private PagingService pagingService;
	
	//관리자 페이지 회원 메인페이지
	@RequestMapping(value = "/admin/memberList", method = RequestMethod.GET)
	public String adminMemberMain(Model model, HttpServletRequest request) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("searchCategory", "noSearch");
		pagingService = new PagingService(request, service.getMemberTotalCount(map), 10);
		map.put("Page", pagingService.getNowPage());
		map.put("PageSize", 10);
		
		List<MemberDTO> adminMemberList = service.getMemberList(map);
		
		model.addAttribute("adminMemberList", adminMemberList);
		model.addAttribute("Paging", pagingService.getPaging());
		
		return "admin/member/memberlist";
	}
	
	//회원 검색
	@RequestMapping(value = "/admin/memberSearchList", method = RequestMethod.GET)
	public String memberSearchList(HttpServletRequest request, Model model) throws Exception {
		String searchKeyword = request.getParameter("searchKeyword");
		String searchCategory = request.getParameter("searchCategory");
		
		System.out.println( searchCategory + "  "+ searchKeyword);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("searchCategory", searchCategory);
		map.put("searchKeyword", searchKeyword);
		
		pagingService = new PagingService(request, service.getMemberTotalCount(map), 10);
		map.put("Page", pagingService.getNowPage());
		map.put("PageSize", 10);
		
		List<MemberDTO> adminMemberList = service.getMemberList(map);
		
		model.addAttribute("adminMemberList", adminMemberList);
		model.addAttribute("Paging", pagingService.getPaging());
		model.addAttribute("searchCategory", searchCategory);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "admin/member/memberlist";
	}
	
	//관리자 페이지 회원 상세 페이지
	@RequestMapping(value = "/admin/memberDetail", method = RequestMethod.GET)
	public String adminMemberDetail(Model model, HttpServletRequest request) throws Exception {
		int m_no = Integer.parseInt(request.getParameter("m_no"));
		model.addAttribute("mDetail", service.getMemberView(m_no));
		
		return "admin/member/memberView";
	}
	
	//회원 삭제
	@RequestMapping(value = "/admin/memberSelectDelete", method = RequestMethod.GET )
	@ResponseBody
	public int MemberSelectDelete(HttpServletRequest request) throws Exception {
		
        int[] ajaxMsg = Arrays.stream(request.getParameterValues("valueArr")).mapToInt(Integer::parseInt).toArray();
        int delResult = 0, ajaxResult = 0;
        int size = ajaxMsg.length;
        for(int i=0; i<size; i++) {
        	delResult = service.deleteMember(ajaxMsg[i]); //DB에서 삭제
        	if(delResult == 1) {
        		ajaxResult += 1;
        	}else {
        		System.out.println("회원 삭제 문제");
        	}
        }
        if(size != ajaxResult){
        	return 0;
        }else {
        	return 1;
        }
	}
	
}