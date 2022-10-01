package com.spring.ex.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.ex.dto.HistoryOrderLectureDTO;
import com.spring.ex.service.HistoryOrderService;

@Controller
public class PurchaseController {
	
	@Inject
	HistoryOrderService historyOrderService;
	
	@RequestMapping(value = "/purchaseCourse", method = RequestMethod.POST)
	public String purchaseCourse(HistoryOrderLectureDTO dto) throws Exception {
		
		historyOrderService.insertHistoryOrderLecture(dto);
		
		return "redirect:/courses/" + dto.getOli_no();
	}
}
