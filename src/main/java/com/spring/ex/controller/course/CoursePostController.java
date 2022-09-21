package com.spring.ex.controller.course;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.dto.TeacherDTO;
import com.spring.ex.dto.course.CourseDTO;
import com.spring.ex.dto.course.CourseTagDTO;
import com.spring.ex.dto.course.CourseVideoDTO;
import com.spring.ex.service.CourseService;
import com.spring.ex.service.FileService;
import com.spring.ex.service.t_TagService;

@Controller
public class CoursePostController {
	//--------------------------------
	// 강의 상세 페이지 관리 컨트롤러
	//--------------------------------
	
	@Inject
	private CourseService courseService; 
	
	@Inject
	private FileService fileService;
	
	@Inject
	private t_TagService tagService;
	
	@Resource(name="courseImagePath")
	String courseImagePath;
	
	// 강의 생성 페이지
	@RequestMapping("/writeCourse")
	public String writeCourse(HttpServletRequest request, Model model) {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강사 자격이 없습니다.");
			return "error";
		}
		
		model.addAttribute("actionURL", "/submitCourse");
		model.addAttribute("allTagList", tagService.getTagList());
		
		return "course/course_write";
	}
	
	// 강의 등록 페이지
	@RequestMapping("/submitCourse")
	public String submitCourse(HttpServletRequest request, Model model, @RequestParam("thumbnail") MultipartFile thumbnail) throws Exception {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강의 생성 권한이 없습니다.");
			return "error";
		}
		
		System.out.println("등록 시작");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String[] tags = request.getParameterValues("tags");
		String[] videoTitles = request.getParameterValues("video_titles");
		String[] videoPaths = request.getParameterValues("video_paths");
	
		String img_path = null;
		if(!thumbnail.isEmpty()) { 
			img_path = fileService.insertFileToLocalAndServer(thumbnail, courseImagePath);
		}
		
		if(memberDTO == null || title == null || content == null || img_path == null || price == null) {
			System.out.println("빈 칸이 있습니다.");
			return "error";
		}
		
		// 강의 등록
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setOlt_no(teacherDTO.getOlt_no());
		courseDTO.setTitle(title);
		courseDTO.setContent(content);
		courseDTO.setImg_path(img_path);
		courseDTO.setPrice(Integer.parseInt(price));
		courseDTO.setReg_date(new Date(System.currentTimeMillis()));
		courseDTO.setEnable(1);
		
		courseService.submitCourse(courseDTO);
		
		for(String t : tags) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(courseDTO.getOli_no());
			courseTagDTO.setTag_no(tagService.getTagByTag_abbr(t).getTag_no());
			courseService.submitCourseTag(courseTagDTO);
		}
		
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO courseVideoDTO = new CourseVideoDTO();
			courseVideoDTO.setOli_no(courseDTO.getOli_no());
			courseVideoDTO.setTitle(videoTitles[i]);
			courseVideoDTO.setS_file_name(videoPaths[i]);
			
			courseService.submitCourseVideo(courseVideoDTO);
		}
		
		System.out.println("강의 등록 성공");
		System.out.println("등록 내용 : " + courseDTO);
		
		// 게시글의 파일 관리
		fileService.manageFileAfterPostSubmission(content, courseDTO.getOli_no(), 1); // category 강의 : 1

		return "redirect:/course/"+courseDTO.getOli_no();
	}
	
	// 강의 수정 페이지
	@RequestMapping("/rewriteCourse")
	public String rewriteCourse(HttpServletRequest request, Model model) {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("강의를 찾을 수 없습니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강사 자격이 없습니다.");
			return "error";
		}
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		if(courseDTO == null) {
			System.out.println("존재하지 않는 강의입니다.");
			return "error";
		}
		
		if(teacherDTO.getOlt_no() != courseDTO.getOlt_no()) {
			System.out.println("강의 수정 권한이 없습니다.");
			return "error";
		}
		
		List<CourseTagDTO> myTagList = courseService.getCourseTags(pageNo);
		List<CourseVideoDTO> videoList = courseService.getCourseVideoList(pageNo);
		
		model.addAttribute("course", courseDTO);
		model.addAttribute("allTagList", tagService.getTagList());
		model.addAttribute("myTagList", myTagList);
		model.addAttribute("videoList", videoList);
		model.addAttribute("courseService", courseService);
		model.addAttribute("actionURL", "/updateCourse");
		return "course/course_write";
	}
	
	@RequestMapping("/updateCourse")
	public String updateCourse(HttpServletRequest request, Model model, @RequestParam("thumbnail") MultipartFile thumbnail) throws Exception {
		String _pageNo = request.getParameter("pageNo");
		if(_pageNo == null) {
			System.out.println("강의를 찾을 수 없습니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강사 자격이 없습니다.");
			return "error";
		}
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		if(courseDTO == null) {
			System.out.println("존재하지 않는 강의입니다.");
			return "error";
		}
		
		if(teacherDTO.getOlt_no() != courseDTO.getOlt_no()) {
			System.out.println("강의 수정 권한이 없습니다.");
			return "error";
		}
		
		System.out.println("수정 시작");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String[] tags = request.getParameterValues("tags");
		String[] videoTitles = request.getParameterValues("video_titles");
		String[] videoPaths = request.getParameterValues("video_paths");
	
		String img_path = null;
		if(!thumbnail.isEmpty()) { 
			img_path = fileService.insertFileToLocalAndServer(thumbnail, "/img/course/uploaded_images");
			fileService.deleteFileToLocalAndServer(courseDTO.getImg_path());
		}
		
		if(memberDTO == null || title == null || content == null || price == null) {
			System.out.println("빈 칸이 있습니다.");
			return "error";
		}
		
		// 강의 수정
		courseDTO.setTitle(title);
		courseDTO.setContent(content);
		if(img_path != null) 
			courseDTO.setImg_path(img_path);
		courseDTO.setPrice(Integer.parseInt(price));
		courseDTO.setReg_date(new Date(System.currentTimeMillis()));
		courseDTO.setEnable(1);
		
		courseService.updateCourse(courseDTO);
		
		courseService.deleteCourseTag(pageNo);
		for(String t : tags) {
			CourseTagDTO courseTagDTO = new CourseTagDTO();
			courseTagDTO.setOli_no(courseDTO.getOli_no());
			courseTagDTO.setTag_no(tagService.getTagByTag_abbr(t).getTag_no());
			courseService.submitCourseTag(courseTagDTO);
		}
		
		courseService.deleteCourseVideo(pageNo);
		for(int i = 0; i < videoTitles.length; i++) {
			CourseVideoDTO courseVideoDTO = new CourseVideoDTO();
			courseVideoDTO.setOli_no(courseDTO.getOli_no());
			courseVideoDTO.setTitle(videoTitles[i]);
			courseVideoDTO.setS_file_name(videoPaths[i]);
			courseService.submitCourseVideo(courseVideoDTO);
		}
		
		System.out.println("강의 수정 성공");
		System.out.println("수정 내용 : " + courseDTO);
		
		// 게시글의 파일 관리
		fileService.manageFileAfterPostSubmission(content, courseDTO.getOli_no(), 1);
		
		return "redirect:/course/"+pageNo;
	}
	
	// 강의 수정 취소
	@RequestMapping("/cancelCourse")
	public void cancelCourse(HttpServletRequest request) throws Exception {
		System.out.println("강의 수정 취소");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		
		// 게시글의 파일 관리
		fileService.manageFileAfterPostSubmission(courseDTO.getContent(), courseDTO.getOli_no(), 1);
	}
	
	// 게시물 삭제
	@RequestMapping("/deleteCourse")
	public String deleteCourse(HttpServletRequest request) throws Exception {
		String _pageNo = request.getParameter("no");
		if(_pageNo == null) {
			System.out.println("강의를 찾을 수 없습니다.");
			return "error";
		}
		int pageNo = Integer.parseInt(_pageNo);
		
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		if(memberDTO == null) {
			System.out.println("로그인이 필요합니다.");
			return "error";
		}
		
		TeacherDTO teacherDTO = courseService.getTeacherInfoByM_no(memberDTO.getM_no());
		if(teacherDTO == null) {
			System.out.println("강사 자격이 없습니다.");
			return "error";
		}
		
		CourseDTO courseDTO = courseService.getCourseDetail(pageNo);
		if(courseDTO == null) {
			System.out.println("존재하지 않는 강의입니다.");
			return "error";
		}
		
		if(teacherDTO.getOlt_no() != courseDTO.getOlt_no()) {
			System.out.println("강의 삭제 권한이 없습니다.");
			return "error";
		}
		// 섬네일 삭제
		fileService.deleteFileToLocalAndServer(courseDTO.getImg_path());
		fileService.deleteAllFileOfPost(pageNo, 1);
		courseService.deleteCourse(pageNo);
		System.out.println("강의 삭제 성공");
		
		return "redirect:/courses";
	}
	
	// 서버에만 이미지 임시로 저장
	@ResponseBody
	@RequestMapping(value="/courseUploadSummernoteImageFile", produces = "application/json; charset=utf8")
	public String courseUploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request ) throws Exception {
		JsonObject jsonObject = new JsonObject();
		// 내부경로로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/resources");
		String fileRoot = contextRoot + courseImagePath;
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		File serverFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, serverFile);	// 서버에 파일 저장
			jsonObject.addProperty("url", "/resources" + courseImagePath + savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
		} catch (IOException e) {
			FileUtils.deleteQuietly(serverFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		} 
		String a = jsonObject.toString();
		return a;
	}
}
