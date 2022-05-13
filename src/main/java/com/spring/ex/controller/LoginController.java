package com.spring.ex.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ex.dto.MemberDTO;
import com.spring.ex.service.MemberService;


@Controller
public class LoginController {
	
	@Inject
	MemberService service;
	
	@RequestMapping(value="/loginPageView", method = RequestMethod.GET)
	public String getLogin(MemberDTO dto) throws Exception {
		
		return "/login/login";
	}
	
	// 로그인
	@RequestMapping(value = "/loginPageView", method = RequestMethod.POST)
	public String postLogin(MemberDTO dto, HttpServletRequest req, RedirectAttributes rttr) throws Exception {

		HttpSession session = req.getSession();

		MemberDTO login = service.login(dto);
		
		if (login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		} else {
			session.setAttribute("member", login);
		}

		return "redirect:/";
	}
	
	@RequestMapping(value="/signUp", method = RequestMethod.GET)
	public String getSignUp() throws Exception {
		return "/login/signUp";
	}	
	
	@RequestMapping(value="/signUp", method = RequestMethod.POST)
	public String postSignUp(MemberDTO dto) throws Exception {
		
		service.signUp(dto);
		
		return "/login/signUp";
	}	
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "/memberIdCheck", method = RequestMethod.POST)
	public int postMemberIdCheck(HttpServletRequest req) throws Exception {

		String m_id = req.getParameter("m_id");
		MemberDTO memberIdCheck = service.memberIdCheck(m_id);

		int result = 0;

		if (memberIdCheck != null) {
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
