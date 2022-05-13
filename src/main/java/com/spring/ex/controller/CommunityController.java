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
	
	@RequestMapping(value = "/chats", method = RequestMethod.GET)
	public String chats(Model model, HttpServletRequest request) throws Exception{
		pagingService = new PagingService(request, cbService.getCommunityBoardTotalCount(), 10);
		List<CommunityBoardDTO> communityBoardList = cbService.getCommunityBoardChatRegDateShowPage(pagingService.getMap());
		List<CommunityBoardDTO> communityBoardChatGoodShow = cbService.getCommunityBoardChatGoodShowPage(pagingService.getMap());
		
		model.addAttribute("cbGoodShowList", communityBoardChatGoodShow);
		model.addAttribute("cbRegDateList", communityBoardList);
		model.addAttribute("Paging", pagingService.getPaging());
		
		return "community/community_chats";
	}
	
	
	@RequestMapping("/questions")
	public String questions() throws Exception {
		
		
		System.out.println(cbService.getCommunityBoardTotalCount());
		return "community/community_questions";
	}
	
	@RequestMapping("/reviews")
	public String reviews() {
		return "community/community_reviews";
	}
	
	@RequestMapping("/studies")
	public String studies() {
		return "community/community_studies";
	}
}
