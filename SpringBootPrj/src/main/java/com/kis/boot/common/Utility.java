package com.kis.boot.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {

	private static String encryptSha256(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		 
		 return sb.toString();
	 }

	 /**
	  * password 암호화( SHA256(SHA256(password)) )
	  * 
      * @throws NoSuchAlgorithmException 
	  */
	 public static String getEncryptedPassword(String input) throws NoSuchAlgorithmException {
		String password = "";
		
		password = encryptSha256(input);
		password = encryptSha256(password);
		
		return password;
	 }
	
}
