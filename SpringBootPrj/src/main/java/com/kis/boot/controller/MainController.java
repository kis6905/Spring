package com.kis.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kis.boot.entity.Member;
import com.kis.boot.repositories.MemberRepository;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MemberRepository memberRepository;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getMain() {
		
		logger.info("-> []");
		
		List<Member> memberList = memberRepository.findAll();
		for (Member member : memberList) {
			System.out.println(member.toString());
		}
		
		logger.info("<- []");
		return "main";
	}
	
}
