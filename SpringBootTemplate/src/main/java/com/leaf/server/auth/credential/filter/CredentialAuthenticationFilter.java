package com.leaf.server.auth.credential.filter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaf.server.sample.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CredentialAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final ObjectMapper objectMapper;

	public CredentialAuthenticationFilter(RequestMatcher requestMatcher, ObjectMapper objectMapper) {
		super(requestMatcher);
		this.objectMapper = objectMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
		if (isJson(request)) {
			log.debug("This request is json");
			Member member = objectMapper.readValue(request.getReader(), Member.class);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getRealPassword());
			return getAuthenticationManager().authenticate(authentication);
		} else {
			throw new AccessDeniedException("Don't use content type for " + request.getContentType());
		}
	}

	private boolean isJson(HttpServletRequest request) {
		return MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType());
	}
}
