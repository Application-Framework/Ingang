package com.spring.ex.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.spring.ex.dto.CommunityBoardDTO;
import com.spring.ex.dto.CommunityBoardReplyDTO;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.service.CommunityBoardService;
import com.spring.ex.service.PagingService;

@Controller
public class CommunityController {
	@Inject
	private CommunityBoardService cbService;
	
	private PagingService pagingService;
	
	@RequestMapping(value = "/communityChats", method = RequestMethod.GET)
	public String chats(Model model, HttpServletRequest request) throws Exception{
		pagingService = new PagingService(request, cbService.getCommunityBoardTotalCount(), 10);
		HashMap<String, Object> map = new HashMap<String, Object>(); 
		map.put("Page",  pagingService.getNowPage());
		map.put("PageSize", 10);
		
		List<CommunityBoardDTO> cbRegDateList = cbService.getCommunityBoardChatRegDateShowPage(map);
		List<CommunityBoardDTO> communityBoardChatGoodShow = cbService.getCommunityBoardChatGoodShowPage(map);
		
		model.addAttribute("cbRegDateList", cbRegDateList);
		model.addAttribute("cbGoodShowList", communityBoardChatGoodShow);
		model.addAttribute("Paging", pagingService.getPaging());
		
		return "community/communityChats";
	}
	
	//게시글 상세 페이지
	@RequestMapping(value = "/communityBoardRead", method = RequestMethod.GET)
	public String communityBoardRead(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		int cb_no = Integer.parseInt(request.getParameter("cb_no"));
		int classify = Integer.parseInt(request.getParameter("classify"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cb_no", cb_no);
		map.put("classify", classify);
		
		if(request.getSession().getAttribute("member") != null) {
			MemberDTO memberDto = (MemberDTO) request.getSession().getAttribute("member");
			HashMap<String, Integer> goodCheckmap = new HashMap<String, Integer>();
			goodCheckmap.put("cb_no", cb_no);
			goodCheckmap.put("m_no", memberDto.getM_no());
			System.out.println("좋아요 회원/게시글" + goodCheckmap);
			
			int boardLikeCheck = cbService.getGoodCheckReadCommunityBoard(goodCheckmap);
			model.addAttribute("boardLikeCheck", boardLikeCheck);
			System.out.println(boardLikeCheck);
			System.out.println("회원 번호 : " + memberDto.getM_no());
		}
		
		
		
		Cookie viewCookie = null;										//쿠기 조회수 시작
		Cookie[] cookies = request.getCookies();
		if(cookies !=null) {
			for (int i = 0; i < cookies.length; i++) {
				//System.out.println("쿠키 이름 : "+cookies[i].getName());
				if(cookies[i].getName().equals("|"+cb_no+"|")) {		//만들어진 쿠키들을 확인하며, 만약 들어온 적 있다면 생성되었을 쿠키가 있는지 확인
					//System.out.println("if문 쿠키 이름 : "+cookies[i].getName());
					viewCookie=cookies[i];								//찾은 쿠키를 변수에 저장
				}
			}
		}else {
			System.out.println("cookies 확인 로직 : 쿠키가 없습니다.");
		}
		if(viewCookie==null) {												//만들어진 쿠키가 없음을 확인
			//System.out.println("viewCookie 확인 로직 : 쿠키 없당");
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
		model.addAttribute("classify", classify);
		
		return "community/communityRead";
	}
	
	//게시글 좋아요 삭제
	@RequestMapping(value = "/subtractGoodCommunityBoard" , method = RequestMethod.GET)
	public String subtractGoodCommunityBoard(Model model, HttpServletRequest request) throws Exception{
		MemberDTO memberDto = (MemberDTO) request.getSession().getAttribute("member");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", memberDto.getM_no());
		map.put("cb_no",Integer.parseInt(request.getParameter("cb_no")));
		//System.out.println("좋아요 삭제 : "+map);
		cbService.subtractGoodReadCommunityBoard(map);
		return "community/communityRead";
	}
	
	//게시글 좋아요 추가
	@RequestMapping(value = "/addGoodCommunityBoard" , method = RequestMethod.GET)
	public String addGoodCommunityBoard(Model model, HttpServletRequest request) throws Exception{
		MemberDTO memberDto = (MemberDTO) request.getSession().getAttribute("member");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", memberDto.getM_no());
		map.put("cb_no", Integer.parseInt(request.getParameter("cb_no")));
		//System.out.println("좋아요 추가 : "+map);
		cbService.addGoodReadCommunityBoard(map);
		return "community/communityRead";
	}
	
	//게시글 댓글 작성
	@RequestMapping(value = "/writeReplyCommunityBoard" ,  method = RequestMethod.POST)
	public @ResponseBody int writeReplyCommunityBoard(CommunityBoardReplyDTO dto) throws Exception{
		return cbService.writeReplyCommunityBoard(dto);
	}
	
	//게시글 댓글 삭제
	@RequestMapping(value = "/deleteReplyCommunityBoard" ,  method = RequestMethod.POST)
	public  @ResponseBody int deleteReplyCommunityBoard(int cbr_no) throws Exception{
		int result = cbService.deleteReplyCommunityBoard(cbr_no);
		return result;
	}
	
	//게시글 작성 페이지
	@RequestMapping(value = "/communityBoardWrite", method = RequestMethod.GET)
	public String communityBoardWritePage() throws Exception{
		return "community/communityBoardWrite";
	}
	
	//게시글 글작성
	@RequestMapping(value = "/doWriteCommunityBoard", method = RequestMethod.POST)
	@ResponseBody
	public int communityBoardWrite(CommunityBoardDTO dto, HttpServletRequest request) throws Exception{
		int res = cbService.writeCommunityBoard(dto);
		return res;
	}
	
	//게시글 삭제
	@RequestMapping(value = "/deleteCommunityBoard", method = RequestMethod.POST)
	@ResponseBody
	public int deleteCommunityBoard(int cb_no) throws Exception{
		int res = cbService.deleteCommunityBoard(cb_no);
		return res;
	}
	
	//게시글 수정 페이지
	@RequestMapping(value = "/modfiyPageCommunityBoard", method = RequestMethod.GET)
	public String modfiyPageCommunityBoard(Model model, HttpServletRequest request) throws Exception{
		int cb_no = Integer.parseInt(request.getParameter("cb_no"));
		int classify = Integer.parseInt(request.getParameter("classify"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cb_no", cb_no);
		map.put("classify", classify);
		
		
		model.addAttribute("cbReadPage", cbService.getReadCommunityBoard(map));
		return "community/communityBoardModify";
	}
	
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
		JsonObject jsonObject = new JsonObject();
		System.out.println("test upload");
        /*
		 * String fileRoot = "C:\\summernote_image\\"; // 외부경로로 저장을 희망할때.
		 */
		
		// 내부경로로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = contextRoot+"resources/img/community/fileupload/";
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/resources/img/community/fileupload/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Model model, HttpServletRequest request) throws Exception{
		/*
		pagingService = new PagingService(request, cbService.getCommunityBoardTotalCount(), 10);
		List<CommunityBoardDTO> cbRegDateList = cbService.getCommunityBoardChatRegDateShowPage(pagingService.getMap());
		List<CommunityBoardDTO> communityBoardChatGoodShow = cbService.getCommunityBoardChatGoodShowPage(pagingService.getMap());
		
		model.addAttribute("cbRegDateList", cbRegDateList);
		model.addAttribute("cbGoodShowList", communityBoardChatGoodShow);
		
		model.addAttribute("Paging", pagingService.getPaging());
		*/
		return "community/community_test";
	}
	
	
	@RequestMapping("/communityQuestions")
	public String questions() throws Exception {
		
		
		System.out.println(cbService.getCommunityBoardTotalCount());
		return "community/communityQuestions";
	}
	
	@RequestMapping("/communityReviews")
	public String reviews() {
		return "community/communityReviews";
	}
	
	@RequestMapping("/communityStudies")
	public String studies() {
		return "community/communityStudies";
	}
}
