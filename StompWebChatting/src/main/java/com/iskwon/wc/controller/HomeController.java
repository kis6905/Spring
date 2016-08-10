package com.iskwon.wc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHome(Model model) {
		
		logger.info("-> []");
		
		// TODO 홈 화면에서 기본적으로 필요한 데이터(채팅방 목록... 등)
		
		logger.info("<- []");
		return "home";
	}
	
}
