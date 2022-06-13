package com.spring.ex.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.PurchaseCourseDTO;
import com.spring.ex.service.PurchaseCourseService;

@Controller
public class PurchaseController {
	
	@Inject
	PurchaseCourseService purchaseCourseService;
	
	@RequestMapping(value = "/purchaseCourse", method = RequestMethod.POST)
	public String purchaseCourse(PurchaseCourseDTO dto) throws Exception {
		
		purchaseCourseService.purchaseCourse(dto);
		
		return "redirect:/courses/" + dto.getOli_no();
	}
}
