package com.iskwon.wc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iskwon.wc.common.Constants;
import com.iskwon.wc.service.LoginService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginController;
	
	/**
	 * 로그인 - 로그인 페이지 이동
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLogin() {
		
		logger.info("-> []");
		
		logger.info("<- []");
		return "login";
	}
	
	/**
	 * 로그인 - 사용자 계정 정보 확인 후 결과 리턴
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String postLogin(
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request) {
		
		logger.info("-> [userId = {}], [password = {}]", userId, password);
		
		JSONObject jsonResult = new JSONObject();
		int result = loginController.getLoginResult(userId, password);
		
		if (result == Constants.LOGIN_SUCCESS) {
			HttpSession session = request.getSession(false);
			session.setAttribute("userId", userId);
		}
		
		jsonResult.put("result", result);
		
		logger.info("<- [jsonResult = {}]", jsonResult.toString());
		return jsonResult.toString();
	}
	
}
