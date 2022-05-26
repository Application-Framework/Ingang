package com.spring.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.PagingService;

@Controller
@RequestMapping("/community")
public class CommunityController {
	@Inject
	CommunityBoardService cbService;
	
	PagingService pagingService;
	
	@RequestMapping(value = "/ChatsPage", method = RequestMethod.GET)
	public String chats(Model model, HttpServletRequest request) throws Exception{
		pagingService = new PagingService(request, cbService.getCommunityBoardTotalCount(), 10);
		List<CommunityBoardDTO> cbRegDateList = cbService.getCommunityBoardChatRegDateShowPage(pagingService.getMap());
		List<CommunityBoardDTO> communityBoardChatGoodShow = cbService.getCommunityBoardChatGoodShowPage(pagingService.getMap());
		
		model.addAttribute("cbRegDateList", cbRegDateList);
		model.addAttribute("cbGoodShowList", communityBoardChatGoodShow);
		
		model.addAttribute("Paging", pagingService.getPaging());
		
		return "community/communityChats";
	}
	
	//게시글 작성 페이지
	@RequestMapping(value = "/BoardWritePage", method = RequestMethod.GET)
	public String communityBoardWritePage(Model model, HttpServletRequest request) throws Exception{
		
		
		return "community/boardWrite";
	}
	
	//게시글 글작성
	@RequestMapping(value = "/BoardWrite", method = RequestMethod.GET)
	public String communityBoardWrite(Model model, HttpServletRequest request) throws Exception{
		
		
		return "";
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model, HttpServletRequest request) throws Exception{
		pagingService = new PagingService(request, cbService.getCommunityBoardTotalCount(), 10);
		List<CommunityBoardDTO> cbRegDateList = cbService.getCommunityBoardChatRegDateShowPage(pagingService.getMap());
		List<CommunityBoardDTO> communityBoardChatGoodShow = cbService.getCommunityBoardChatGoodShowPage(pagingService.getMap());
		
		model.addAttribute("cbRegDateList", cbRegDateList);
		model.addAttribute("cbGoodShowList", communityBoardChatGoodShow);
		
		model.addAttribute("Paging", pagingService.getPaging());
		
		return "community/community_test";
	}
	
	
	@RequestMapping("/questionsPage")
	public String questions() throws Exception {
		
		
		System.out.println(cbService.getCommunityBoardTotalCount());
		return "community/communityQuestions";
	}
	
	@RequestMapping("/reviewsPage")
	public String reviews() {
		return "community/communityReviews";
	}
	
	@RequestMapping("/studiesPage")
	public String studies() {
		return "community/communityStudies";
	}
}
