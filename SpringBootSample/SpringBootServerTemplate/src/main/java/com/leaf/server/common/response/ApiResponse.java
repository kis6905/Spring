package com.leaf.server.common.response;

import lombok.Data;

/**
 * 
 * @author iskwon
 * @since 2018. 6. 18.
 */
@Data
public class ApiResponse {
	
	public enum Result {
		OK("0000", "success"),
		NOK("9999", "fail");
		// can adds result case.
		
		private String code;
		private String message;
		
		Result(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public String getCode() {
			return this.code;
		}
		
		public String getMessage() {
			return this.message;
		}
	}
	
	private String resultCode;
	private String resultMessage;
	private Object data;
	
	public ApiResponse(Result result) {
		this.resultCode = result.getCode();
		this.resultMessage = result.getMessage();
	}
	
	public ApiResponse(Result result, Object data) {
		this.resultCode = result.getCode();
		this.resultMessage = result.getMessage();
		if (data != null) {
			this.data = data;
		}
	}
}
