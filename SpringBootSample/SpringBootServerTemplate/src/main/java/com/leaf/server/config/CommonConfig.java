package com.leaf.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @since 2018. 6. 19.
 * @author iskwon
 */
@Configuration
public class CommonConfig {
	
	public static String mode;
	private static String jwtEnable;

	@Value("${spring.profiles.active}")
	public void setMode(String param) {
		mode = param;
	}
	
	@Value("${jwt.enable}")
	public void setJwtEnable(String param) {
		jwtEnable = param;
	}
	
	public static boolean isDev() {
		return  (mode != null && mode.equals("D"));
	}
	
	public static boolean isEnableJwt() {
		return (jwtEnable == null || Boolean.valueOf(jwtEnable));
	}
}
