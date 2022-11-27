package com.spring.ex.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.spring.ex.dto.MemberDTO;
import com.spring.ex.service.MemberService;


@Controller
public class LoginController {
	
	@Inject
	MemberService service;
	
	@RequestMapping(value="/loginPageView", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest request) throws Exception {
		// 이전 페이지 저장
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("redirectURI", referer);
		return "/login/login";
	}
	
	// 로그인
	@ResponseBody
	@RequestMapping(value = "/signIn", method = RequestMethod.POST, produces="application/json; charset=utf8")
	public String signIn(MemberDTO login, HttpServletRequest request) throws Exception {
		JsonObject jsonObject = new JsonObject();
		login = service.login(login);
		
		// 로그인 실패
		if (login == null) {
			System.err.println("로그인 정보가 일치하지 않습니다.");
			jsonObject.addProperty("responseCode", "error");
			jsonObject.addProperty("message", "로그인 정보가 일치하지 않습니다.");
			return jsonObject.toString();
		} 
		// 로그인 성공
		else {
			HttpSession session = request.getSession();
			session.setAttribute("member", login);
			session.setAttribute("m_no", login.getM_no());
			
			int m_no = (Integer)session.getAttribute("m_no");
			
			// 접속 기록 추가
			service.insertFirstConMemberLog(m_no);
			
			// 로그인 후 이전 페이지로 이동
			Object redirectURL = session.getAttribute("redirectURI");
			
			// 이전 페이지가 없다면 main 페이지로 이동
			if(redirectURL == null || redirectURL.toString().indexOf("/loginPageView") != -1)
				redirectURL = "/";
			
			System.out.println("로그인 성공 : " + login);
			jsonObject.addProperty("responseCode", "success");
			jsonObject.addProperty("url", redirectURL.toString());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/signUp", method = RequestMethod.GET)
	public String getSignUp() throws Exception {
		return "/login/signUp";
	}	
	
	@RequestMapping(value="/signUp", method = RequestMethod.POST)
	public String postSignUp(MemberDTO dto) throws Exception {
		
		service.signUp(dto);
		
		return "redirect:/";
	}	
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int postMemberIdCheck(HttpServletRequest req) throws Exception {

		String m_id = req.getParameter("m_id");
		MemberDTO idCheck = service.idCheck(m_id);

		int result = 0;

		if (idCheck != null) {
			result = 1;
		}

		return result;
	}
	
	// 로그아웃
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {

		session.invalidate();

		return "redirect:/";
	}
}
