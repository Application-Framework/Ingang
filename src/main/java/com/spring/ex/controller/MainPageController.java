package com.spring.ex.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.TagDTO;
import com.spring.ex.service.TagService;

@Controller
public class MainPageController {
	
	@Inject
	TagService tagService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(HttpServletRequest request, Model model) throws Exception {
		
		List<TagDTO> list = null;
		
		HttpSession session = request.getSession();
		TagDTO tagDTO = (TagDTO)session.getAttribute("tag");
		
		list = tagService.tagRanking(tagDTO);
		
		model.addAttribute("tagList", list);
		
		return "index";
	}
}
