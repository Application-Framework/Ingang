package com.spring.ex.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.MemberDTO;
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
		List<CommunityBoardDTO> cbRegDateList = cbService.getCommunityBoardChatRegDateShowPage(pagingService.getMap());
		List<CommunityBoardDTO> communityBoardChatGoodShow = cbService.getCommunityBoardChatGoodShowPage(pagingService.getMap());
		
		model.addAttribute("cbRegDateList", cbRegDateList);
		model.addAttribute("cbGoodShowList", communityBoardChatGoodShow);
		
		model.addAttribute("Paging", pagingService.getPaging());
		
		return "community/communityChats";
	}
	
	//게시글 상세 페이지
	@RequestMapping(value = "/boardRead", method = RequestMethod.GET)
	public String communityBoardRead(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int cb_no = Integer.parseInt(request.getParameter("cb_no"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cb_no", cb_no);
		map.put("classify", Integer.parseInt(request.getParameter("classify")));
		
		if(request.getSession().getAttribute("member") != null) {
			MemberDTO memberDto = (MemberDTO) request.getSession().getAttribute("member");
			HashMap<String, Object> goodCheckmap = new HashMap<String, Object>();
			map.put("m_no", memberDto.getM_id());
			map.put("cb_no", cb_no);
			//System.out.println("좋아요 회원/게시글" + map);
			
			int boardLikeCheck = cbService.getGoodCheckReadCommunityBoard(goodCheckmap);
			model.addAttribute("boardLikeCheck", boardLikeCheck);
			//System.out.println("회원 번호 : " + memberDto.getM_id());
		}
		
		
		
		Cookie viewCookie = null;										//쿠기 조회수 시작
		Cookie[] cookies = request.getCookies();
		if(cookies !=null) {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println("쿠키 이름 : "+cookies[i].getName());
				if(cookies[i].getName().equals("|"+cb_no+"|")) {		//만들어진 쿠키들을 확인하며, 만약 들어온 적 있다면 생성되었을 쿠키가 있는지 확인
					System.out.println("if문 쿠키 이름 : "+cookies[i].getName());
					viewCookie=cookies[i];								//찾은 쿠키를 변수에 저장
				}
			}
		}else {
			System.out.println("cookies 확인 로직 : 쿠키가 없습니다.");
		}
		if(viewCookie==null) {												//만들어진 쿠키가 없음을 확인
			System.out.println("viewCookie 확인 로직 : 쿠키 없당");
			Cookie newCookie=new Cookie("|"+cb_no+"|","readCount");	//이 페이지에 왔다는 증거용(?) 쿠키 생성
			response.addCookie(newCookie);
			cbService.addReadCommunityBoardHit(cb_no);				//쿠키가 없으니 증가 로직 진행
			model.addAttribute("hitReadPage", 1);
		} else {
			model.addAttribute("hitReadPage", 0);
			System.out.println("viewCookie 확인 로직 : 쿠키 있당");			//만들어진 쿠키가 있으면 증가로직 진행하지 않음
		} 																	// 쿠키 조회수 종료
		
		model.addAttribute("cbReadPage", cbService.getReadCommunityBoard(map));
		model.addAttribute("cbrList", cbService.getReplyCommunityBoard(cb_no));
		
		return "community/communityRead";
	}
	
	//게시글 좋아요 삭제
	@RequestMapping(value = "/subtractGoodCommunityBoard" , method = RequestMethod.GET)
	public String subtractGoodCommunityBoard(Model model, HttpServletRequest request) throws Exception{
		MemberDTO memberDto = (MemberDTO) request.getSession().getAttribute("member");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", memberDto.getM_id());
		map.put("cb_no", request.getParameter("cb_no"));
		cbService.subtractGoodReadCommunityBoard(map);
		
		return "boardRead";
	}
	
	//게시글 좋아요 추가
	@RequestMapping(value = "/addGoodCommunityBoard" , method = RequestMethod.GET)
	public String addGoodCommunityBoard(Model model, HttpServletRequest request) throws Exception{
		MemberDTO memberDto = (MemberDTO) request.getSession().getAttribute("member");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", memberDto.getM_id());
		map.put("cb_no", request.getParameter("cb_no"));
		cbService.addGoodReadCommunityBoard(map);
		return "boardRead";
	}
	
	
	//게시글 작성 페이지
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public String communityBoardWritePage(Model model, HttpServletRequest request) throws Exception{
		
		
		return "community/communityBoardWrite";
	}
	
	//게시글 글작성
	@RequestMapping(value = "/boardWriteDo", method = RequestMethod.GET)
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
	
	
	@RequestMapping("/questions")
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
