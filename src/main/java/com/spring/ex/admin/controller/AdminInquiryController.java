package com.spring.ex.admin.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.dto.InquiryDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.PagingService;

@Controller 
public class AdminInquiryController {
	
	@Inject private CommunityBoardService cbService;
	
	private PagingService pagingService;
	
	
	//관리자 페이지 회원 메인페이지
	@RequestMapping(value = "/admin/inquiry", method = RequestMethod.GET)
	public String adminInquiryList(Model model, HttpServletRequest request) throws Exception {
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		HashMap<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("searchType", searchType);
		map.put("searchKeyword", searchKeyword);
		
		pagingService = new PagingService(request, cbService.getCommunityBoardInquiryPageTotalCount(map), 10);
		map.put("Page",  pagingService.getNowPage());
		map.put("PageSize", 10);
		List<InquiryDTO> cbInquiry = cbService.getCommunityBoardInquiryPage(map);
		
		if (searchKeyword == null && searchType == null) {
			model.addAttribute("searchType", "no");
			model.addAttribute("searchKeyword", "no");
		}else {
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchKeyword", searchKeyword);
		}
		
		model.addAttribute("cbInquiry", cbInquiry);
		model.addAttribute("Paging", pagingService.getPaging());
		return "admin/inquiry/inquiry";
	}
	
	
	//관리자 페이지 1:1문의 상세 페이지
	@RequestMapping(value = "/admin/inquiryView", method = RequestMethod.GET)
	public String adminInquiryView(Model model, HttpServletRequest request) throws Exception {
		int inq_no = Integer.parseInt(request.getParameter("inq_no"));
		model.addAttribute("cbReadPage", cbService.getInquiryViewPage(inq_no));
		
		return "admin/inquiry/inquiryView";
	}
	
	//1:1문의 삭제
	@RequestMapping(value = "/admin/inquirySelectDelete", method = RequestMethod.GET )
	@ResponseBody
	public int inquirySelectDelete(HttpServletRequest request) throws Exception {
		int[] ajaxMsg = Arrays.stream(request.getParameterValues("valueArr")).mapToInt(Integer::parseInt).toArray();
		int delResult = 0, ajaxResult = 0;
		int size = ajaxMsg.length;
		for(int i=0; i<size; i++) {
			delResult = cbService.deleteInquiry(ajaxMsg[i]); //DB에서 삭제
			if(delResult == 1) {
				ajaxResult += 1;
			}else {
				System.out.println("문의 내역 삭제 문제");
			}
		}
		if(size != ajaxResult){
			return 0;
		}else {
			return 1;
		}
	}
	
}