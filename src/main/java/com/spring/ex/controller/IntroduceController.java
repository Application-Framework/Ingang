package com.spring.ex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/introduce/*")
public class IntroduceController {
	
	@RequestMapping(value = "/company_info", method = RequestMethod.GET)
	public String chats(Model model, HttpServletRequest request) throws Exception{
		
		return "introduce/company_info";
	}
	
	@RequestMapping("/company_ci")
	public String questions() throws Exception {
		
		return "introduce/company_ci";
	}
	
	@RequestMapping("/company_road")
	public String reviews() throws Exception {
		return "introduce/company_road";
	}
}
