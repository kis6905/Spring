package com.kis.boot.security;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kis.boot.common.Utility;

public class CustomPasswordEncoder implements PasswordEncoder {

private static final Logger logger = LoggerFactory.getLogger(CustomPasswordEncoder.class);
	
	@Override
	public String encode(CharSequence rawPassword) {
		return null;
	}

	@Override
	public boolean matches(CharSequence inputPassword, String memberPassword) {
		logger.info("-> [inputPassword ={}], [memberPassword = {}]", inputPassword, memberPassword);
		try {
			if (inputPassword != null) {
				String encoded = Utility.getEncryptedPassword(inputPassword.toString());
				logger.info("~~ [encoded = {}]", encoded);
				return encoded.equals(memberPassword);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("~~ [An error occurred!]", e);
		}
		return false;
	}

}
