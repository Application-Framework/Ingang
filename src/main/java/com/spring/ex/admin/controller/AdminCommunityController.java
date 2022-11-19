package com.spring.ex.admin.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.admin.service.AdminCommunityService;
import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.course.CourseReplyDTO;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.PagingService;

@Controller 
public class AdminCommunityController {
	
	@Inject AdminCommunityService service;
	
	@Inject
	private CourseService courseService; 
	
	private PagingService pagingService;
	
	
		// 자유게시판
		@RequestMapping(value = "/admin/freeboard", method = RequestMethod.GET)
		public String AdminFreeBoard(HttpServletRequest request, HttpSession session, Model model) throws Exception {
			
			String searchTitle = request.getParameter("s");
			String order = request.getParameter("order");
			
			HashMap<String, Object> countMap = new HashMap<String, Object>();
			countMap.put("searchTitle", searchTitle);
			
			int totalCount = service.getAdminCommunityBoardTotalCount(countMap);
			System.out.println("totalCount : " + totalCount);
			
			final int pageSize = 10;
			
			pagingService = new PagingService(request, totalCount, pageSize);
			HashMap<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("page", pagingService.getNowPage());
			pageMap.put("pageSize", pageSize);
			pageMap.put("order", order);
			pageMap.put("searchTitle", searchTitle);
			
			List<CommunityBoardDTO> cbList = service.getFreeBoardPage(pageMap);//service.adminCommunityBoard(pageMap);
						
			model.addAttribute("cbList", cbList);
			model.addAttribute("courseService", courseService);
			model.addAttribute("paging", pagingService.getPaging()); 
			model.addAttribute("s", searchTitle);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("order", order);
			model.addAttribute("service", service);
			
			return "admin/community/freeboard";
		}
		
		// 질문 답변
		@RequestMapping(value = "/admin/QnA", method = RequestMethod.GET)
		public String AdminQnA(HttpServletRequest request, HttpSession session, Model model) throws Exception {
			
			String searchTitle = request.getParameter("s");
			String order = request.getParameter("order");
			
			HashMap<String, Object> countMap = new HashMap<String, Object>();
			countMap.put("searchTitle", searchTitle);
			
			int totalCount = service.getAdminQnABoardTotalCount(countMap);
			System.out.println("totalCount : " + totalCount);
			
			final int pageSize = 10;
			
			pagingService = new PagingService(request, totalCount, pageSize);
			HashMap<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("page", pagingService.getNowPage());
			pageMap.put("pageSize", pageSize);
			pageMap.put("order", order);
			pageMap.put("searchTitle", searchTitle);
			
			List<CommunityBoardDTO> cbList = service.getQnABoardPage(pageMap);
						
			model.addAttribute("cbList", cbList);
			model.addAttribute("courseService", courseService);
			model.addAttribute("paging", pagingService.getPaging()); 
			model.addAttribute("s", searchTitle);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("order", order);
			model.addAttribute("service", service);
						
			return "admin/community/QnA";
		}
		
	    // 후기
		@RequestMapping(value = "/admin/review", method = RequestMethod.GET)
		public String AdminReview(HttpServletRequest request, HttpSession session, Model model) throws Exception {
			
			String searchTitle = request.getParameter("s");
			String order = request.getParameter("order");
			
			HashMap<String, Object> countMap = new HashMap<String, Object>();
			countMap.put("searchTitle", searchTitle);
			
			int totalCount = service.getAdminReviewBoardTotalCount(countMap);
			System.out.println("totalCount : " + totalCount);
			
			final int pageSize = 10;
			pagingService = new PagingService(request, totalCount, pageSize);
			
			HashMap<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("page", pagingService.getNowPage());
			pageMap.put("pageSize", pageSize);
			pageMap.put("order", order);
			pageMap.put("searchTitle", searchTitle);
			
			List<CourseReplyDTO> olrList = service.getReviewBoardPage(pageMap);
			System.out.println(olrList);
			
			model.addAttribute("olrList", olrList);
			model.addAttribute("courseService", courseService);
			model.addAttribute("paging", pagingService.getPaging()); 
			model.addAttribute("s", searchTitle);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("order", order);
			
			return "admin/community/review";
		}
		
		// 스터디
		@RequestMapping(value = "/admin/study", method = RequestMethod.GET)
		public String AdminStudy(HttpServletRequest request, HttpSession session, Model model) throws Exception {
			
			String searchTitle = request.getParameter("s");
			String order = request.getParameter("order");
			
			HashMap<String, Object> countMap = new HashMap<String, Object>();
			countMap.put("searchTitle", searchTitle);
			
			int totalCount = service.getAdminStudyBoardTotalCount(countMap);
			System.out.println("totalCount : " + totalCount);
			
			final int pageSize = 10;
			
			pagingService = new PagingService(request, totalCount, pageSize);
			HashMap<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("page", pagingService.getNowPage());
			pageMap.put("pageSize", pageSize);
			pageMap.put("order", order);
			pageMap.put("searchTitle", searchTitle);
			
			List<CommunityBoardDTO> cbList = service.getStudyBoardPage(pageMap);
						
			model.addAttribute("cbList", cbList);
			model.addAttribute("courseService", courseService);
			model.addAttribute("paging", pagingService.getPaging()); 
			model.addAttribute("s", searchTitle);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("order", order);
			model.addAttribute("service", service);
						
			
			return "admin/community/study";
		}
		
		//관리자 페이지 회원 상세 페이지
		@RequestMapping(value = "/admin/freeBoardDetail", method = RequestMethod.GET)
		public String adminMemberDetail(Model model, HttpServletRequest request) throws Exception {
			int cb_no = Integer.parseInt(request.getParameter("cb_no"));
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cb_no", cb_no);
			
			//뷰로 보내는 것
			model.addAttribute("cbDetail", service.getBoardView(map));
			
			return "admin/community/boardView";
		}
		
		//관리자 페이지 게시글 작성
		@RequestMapping(value = "/admin/insertAdminBoard", method = RequestMethod.POST)
		@ResponseBody
		public int signUpAdminMember(CommunityBoardDTO cDto, HttpServletRequest request) throws Exception {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String classify = request.getParameter("classify");
			int flag = Integer.parseInt(request.getParameter("flag"));
			
			System.out.println("파라미터  : " + title + "/" + content + "/" + classify + "/" + flag);
			
			cDto.setTitle(title);
			cDto.setContent(content);
			cDto.setClassify(classify);
			cDto.setflag(flag);
			
			System.out.println("DTO 값  : " + cDto);
			
			int res = service.insertAdminBoard(cDto);
			
			return 0;
		}
		
		//관리자 페이지 게시글 수정
		@RequestMapping(value = "/admin/updateAdminBoard", method = RequestMethod.POST)
		@ResponseBody
		public int updateAdminMember(CommunityBoardDTO cDto , HttpServletRequest request)  throws Exception {
			
			int cb_no = (Integer.parseInt(request.getParameter("cb_no")));
			System.out.println("게시글 번호 :" + request.getParameter("cb_no"));
			System.out.println("제목 :" + request.getParameter("title"));
			System.out.println("내용 :" + request.getParameter("content"));
			
			cDto.setCb_no(cb_no);
			cDto.setTitle(cDto.getTitle());
			cDto.setContent(cDto.getContent());
			cDto.setClassify(cDto.getClassify());
			
			int res = service.updateAdminBoard(cDto);
			//System.out.println(mDto.getM_pw() + " " + mDto.getM_comment() + " " + mDto.getM_authority() + mDto.getImg_path());
			return res;
		}
		
		//게시물 삭제
		@RequestMapping(value = "/admin/boardSelectDelete", method = RequestMethod.GET )
		@ResponseBody
		public int BoardSelectDelete(HttpServletRequest request) throws Exception {
			
	        int[] ajaxMsg = Arrays.stream(request.getParameterValues("valueArr")).mapToInt(Integer::parseInt).toArray();
	        int delResult = 0, ajaxResult = 0;
	        int size = ajaxMsg.length;
	        for(int i=0; i<size; i++) {
	        	delResult = service.deleteAdminBoard(ajaxMsg[i]); //DB에서 삭제
	        	if(delResult == 1) {
	        		ajaxResult += 1;
	        	}else {
	        		System.out.println("게시글 삭제 문제");
	        	}
	        }
	        if(size != ajaxResult){
	        	return 0;
	        }else {
	        	return 1;
	        }
		}
		
		//게시물 삭제
		@RequestMapping(value = "/admin/reviewSelectDelete", method = RequestMethod.GET )
		@ResponseBody
		public int ReviewSelectDelete(HttpServletRequest request) throws Exception {
			
		     int[] ajaxMsg = Arrays.stream(request.getParameterValues("valueArr")).mapToInt(Integer::parseInt).toArray();
		     int delResult = 0, ajaxResult = 0;
		     int size = ajaxMsg.length;
		     for(int i=0; i<size; i++) {
		      	delResult = service.deleteAdminReview(ajaxMsg[i]); //DB에서 삭제
		       	if(delResult == 1) {
		       		ajaxResult += 1;
		       	}else {
		       		System.out.println("게시글 삭제 문제");
		       	}
		       }
		       if(size != ajaxResult){
		       	return 0;
		       }else {
		       	return 1;
		       }
		}

}
