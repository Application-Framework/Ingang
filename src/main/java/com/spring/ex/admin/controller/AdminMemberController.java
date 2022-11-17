package com.spring.ex.admin.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.admin.service.AdminMemberService;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.PagingDTO;
import com.spring.ex.service.FileService;
import com.spring.ex.service.MemberService;
import com.spring.ex.service.PagingService;

@Controller 
public class AdminMemberController {
	
	@Inject AdminMemberService service;
	
	@Inject MemberService mService;
	private PagingService pagingService;
	
	@Inject
	private FileService fileUploadService;
	
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
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", m_no);
		
		//강의 구매 페이징
		pagingService = new PagingService(request, service.getMemberOrderLectureTotalCount(map), 5);
		map.put("Page1", pagingService.getNowPage());
		map.put("PageSize1", 5);
		
		//노트 구매 페이징  -  페이징 서비스 바꾸기엔 다른 사람 영향가서 아래처럼 만듬
		int totalCount2 = service.getMemberOrderNoteTotalCount(map);
		int nPage = request.getParameter("nPage") == null ? 1 : Integer.parseInt(request.getParameter("nPage"));
		PagingDTO nPaging = new PagingDTO();
		nPaging .setPageNo(nPage);
		nPaging .setPageSize(5);
		nPaging .setTotalCount(totalCount2);
		nPage = (nPage - 1) * 5;
		map.put("Page2", nPage);
		map.put("PageSize2", nPaging .getPageSize());
		
		//커뮤니티 게시글 페이징
		int totalCount3 = service.getMemberCommunityTotalCount(map);
		int cPage = request.getParameter("cPage") == null ? 1 : Integer.parseInt(request.getParameter("cPage"));
		PagingDTO cPaging = new PagingDTO();
		cPaging.setPageNo(cPage);
		cPaging.setPageSize(5);
		cPaging.setTotalCount(totalCount3);
		cPage  = (cPage - 1) * 5;
		map.put("Page3", cPage);
		map.put("PageSize3", cPaging.getPageSize());
		
		List<Map<String, Object>> adminMemberLectureList = service.getMemberOrderLecture(map);
		List<Map<String, Object>> adminMemberNoteList = service.getMemberOrderNote(map);
		List<Map<String, Object>> adminMemberCommunityList = service.getMemberCommunity(map);
		
		//뷰로 보내는 것
		model.addAttribute("mDetail", service.getMemberView(m_no));
		
		model.addAttribute("adminMemberLectureList", adminMemberLectureList);
		model.addAttribute("Paging", pagingService.getPaging());
		model.addAttribute("adminMemberNoteList", adminMemberNoteList);
		model.addAttribute("Paging2",  nPaging);
		model.addAttribute("adminMemberCommunityList", adminMemberCommunityList);
		model.addAttribute("Paging3",  cPaging);
		
		
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
	//관리자 페이지 회원 수정
	@RequestMapping(value = "/admin/updateAdminMember", method = RequestMethod.POST)
	@ResponseBody
	public int updateAdminMember(MemberDTO mDto , MultipartFile file, HttpServletRequest request)  throws Exception {
		String fileName = null;
		
		if(!file.isEmpty()) {
			fileName = fileUploadService.insertFileToLocalAndServer(file, "/img/profile/uploaded_images");
			//System.out.println(fileName);
			mDto.setImg_path(fileName);
		}
		mDto.setM_comment(mDto.getM_comment().trim());
		int res = service.updateAdminMember(mDto);
		//System.out.println(mDto.getM_pw() + " " + mDto.getM_comment() + " " + mDto.getM_authority() + mDto.getImg_path());
		return res;
	}
	
	//관리자 페이지 회원 가입
	@RequestMapping(value = "/admin/signUpAdminMember", method = RequestMethod.POST)
	@ResponseBody
	public int signUpAdminMember(MemberDTO mDto,  MultipartFile file, HttpServletRequest request) throws Exception {
		String fileName = null;
		
		if(!file.isEmpty()) {
			fileName = fileUploadService.insertFileToLocalAndServer(file, "/img/profile/uploaded_images");
			//System.out.println(fileName);
			mDto.setImg_path(fileName);
		}
		mDto.setM_comment(mDto.getM_comment().trim());
		int res = service.signUpAdminMember(mDto);
		
		return res;
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "/admin/idCheck", method = RequestMethod.POST)
	public int AdminMemberIdCheck(HttpServletRequest request) throws Exception {
		String m_id = request.getParameter("m_id");
		
		MemberDTO idCheck = mService.idCheck(m_id);
		int result;

		if (idCheck == null) {
			result = 1;
		} else {
			result = 0;
		}

		return result;
	}
}