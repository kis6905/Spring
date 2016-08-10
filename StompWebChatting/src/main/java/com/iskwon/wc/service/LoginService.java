package com.iskwon.wc.service;

import org.springframework.stereotype.Service;

import com.iskwon.wc.common.Constants;

@Service
public class LoginService {
	
	/**
	 * 사용자 계정 확인 후 결과 리턴
	 */
	public int getLoginResult(String userId, String password) {
		int resultCode = Constants.LOGIN_SUCCESS;
		
		// TODO 입력한 정보 확인
		
		return resultCode;
	}
	
}
