package com.leaf.server.sample.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * security 인증 url 인 /login 호출 시 json으로 "username", "password"를 보내는데,
 * '@JsonIgnoreProperties'에 "password"가 있으면 objectMapper가 password 값을 넣어주지 않는다.
 * 이 때문에 "password" 필드는 그대로 두되, getPassword()는 빈 값을 리턴해 노출시키지 않으며,
 * 실제 password 값이 필요한 경우 getRealPassword()를 호출하고 '@JsonIgnoreProperties'에 추가해 json 필드에 넣어주지 않는다. 
 * </pre>
 *
 * @author iskwon
 * @since 2018. 6. 18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "realPassword" })
public class Member {
	private String username;
	private Integer age;
	private String phoneNumber;
	private String password;
	private String role;
	
	public String getPassword() {
		return "";
	}
	
	public String getRealPassword() {
		return this.password;
	}
}
