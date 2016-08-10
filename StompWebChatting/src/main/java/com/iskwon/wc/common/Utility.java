package com.iskwon.wc.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	private static String swearwordRegex = "^*(씨발)|(씨 발)|(씨이발)|(시발)|(시 발)|"
										   + "(병신)|(새끼)|(좆)|(애미)|(에미)|(니앰)|(니엠)|"
										   + "(개새)|(개새끼)|(개 새끼)|(개 새 끼)|(개새 끼)+";
	
	/**
	 * 현재 시간을 입력받은 포맷으로 스트링 변환하여 리턴
	 * @param format
	 * @return
	 */
	public static String getCurrentDateString(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	/**
	 * 비속어 필터링
	 * @param content
	 * @return
	 */
	public static String swearwordFilter(String content) {
		return content.replaceAll(swearwordRegex, "**");
	}
	
}
